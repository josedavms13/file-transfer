package main.client.emitter;

import enums.ClientType;
import enums.StatusCode;
import main.client.Client;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CliEmitter extends Client {

    ClientType clientType = ClientType.EMITTER_CLI;
    boolean hasServerEmittedRight = false;

    public CliEmitter(String host, String path, int port) throws IOException {
        super(host, path, port);

        sendClientTypeAndFile();
    }


    private void sendClientTypeAndFile() {

        // Get all content bytes

        //Client type
        byte[] clientTypeBytes = this.clientType.name().getBytes(StandardCharsets.UTF_8);

        // File Name
        String fileName = this.file.getName();
        byte[] fileNameBytes = fileName.getBytes();

        // File
        byte[] fileBytes = new byte[(int) this.file.length()];

        try {
            // Sending ClientType
            dataOutputStream.writeInt(clientTypeBytes.length);
            dataOutputStream.write(clientTypeBytes);

            // Sending File Name
            dataOutputStream.writeInt(fileNameBytes.length);
            dataOutputStream.write(fileNameBytes);

            // Sending File
            dataOutputStream.writeInt(fileBytes.length);
            dataOutputStream.write(fileBytes);

            // Wait to se receivers
            int statusResponse = dataInputStream.readInt();
            if (statusResponse == StatusCode.RECEIVED.code) {
                System.out.println("File received successfully!");
                Thread listening = this.waitForMessages();
                this.sendMessage(listening);


            } else {
                int statusMessageLength = dataInputStream.readInt();
                byte[] statusMessageBytes = new byte[statusMessageLength];
                dataInputStream.readFully(statusMessageBytes, 0, statusMessageLength);
                String statusMessage = new String(statusMessageBytes);
                System.out.println(statusMessage);

            }


        } catch (IOException e) {
            closeEverything();
            throw new RuntimeException(e);
        }
    }

    private Thread waitForMessages() {
        System.out.println("waiting receivers...");
        return new Thread(() -> {
            try {
                String incommingMessage = in.readLine();
                System.out.println(incommingMessage + " receivers pending");
            } catch (IOException e) {
                closeEverything();
                throw new RuntimeException(e);
            }
        });
    }

    private void sendMessage(Thread listening) throws IOException {
        new Thread(() -> {
            while (!hasServerEmittedRight) {
                System.out.println("Press enter to send");
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
                try {
                    dataOutputStream.writeInt(StatusCode.DISPATCH_FILE.code);
                    int serverStatusCode = dataInputStream.readInt();
                    if (serverStatusCode == StatusCode.OK.code) {
                        hasServerEmittedRight = true;
                        listening.interrupt();
                    } else {
                        System.out.println("Receivers aren't ready yet, pleas wait");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }

}
