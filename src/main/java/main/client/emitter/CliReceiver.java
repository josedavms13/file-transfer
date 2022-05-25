package main.client.emitter;

import com.google.common.io.Files;
import enums.ClientType;
import enums.StatusCode;
import main.client.Client;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class CliReceiver extends Client {

    ClientType clientType = ClientType.RECEIVER;

    private Scanner scanner = new Scanner(System.in);
    private String path;
    private String fileName;

    public CliReceiver(String host, int port) throws IOException {

        super(host, port);

        setUp();
    }

    private void setUp() {
        // Receive the server message
        System.out.println("Sat up as CLI RECEIVER");

        //Client type
        byte[] clientTypeBytes = this.clientType.name().getBytes(StandardCharsets.UTF_8);

        try {
            // Sending ClientType
            dataOutputStream.writeInt(clientTypeBytes.length);
            dataOutputStream.write(clientTypeBytes);

            // Message from server asking the path to save
            String input = in.readLine();
            System.out.println( input);
            path = scanner.nextLine();

            // Send to server status code ok
            dataOutputStream.writeInt(StatusCode.READY_TO_RECEIVE.code);

            // Server send fileName to make the path
            fileName = in.readLine();
            path = path + "\\" + fileName;

            //Receive file
            File file = new File(path);
            int fileLength = dataInputStream.readInt();
            byte[] fileBytes = new byte[fileLength];
            dataInputStream.readFully(fileBytes, 0, fileLength);

            Files.write(fileBytes, file);

            if (file.exists()) {
                dataOutputStream.writeInt(StatusCode.RECEIVED.code);
            } else {
                dataOutputStream.writeInt(StatusCode.FAILED.code);
            }
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
