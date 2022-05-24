package main.server;

import enums.ClientType;
import main.server.clients.Emitter;
import main.server.clients.Receiver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.format;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;


    protected DataInputStream dataInputStream;
    protected DataOutputStream dataOutputStream;


    private Emitter emmiter;
    private List<Receiver> receivers = new ArrayList<>();



    private String fileName;
    private byte[] fileBytes;
    boolean isServerAlsoReceiver;

    private boolean fileReady = false;
    private boolean isEmitterReady = false;
    private boolean isReceiversReady = false;

    public Server(int port) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            while (!fileReady) {
                clientSocket = serverSocket.accept();

                dataInputStream = new DataInputStream(clientSocket.getInputStream());

                //Client type
                int clientTypeLength = dataInputStream.readInt();
                if (clientTypeLength > 0) {
                    byte[] clientTypeLengthBytes = new byte[clientTypeLength];
                    dataInputStream.readFully(clientTypeLengthBytes, 0, clientTypeLength);
                    String clientType = new String(clientTypeLengthBytes);

                    if (clientType.equalsIgnoreCase(ClientType.EMITTER_CLI.toString())) {
                        this.clientEmitterSetUp();

                    } else if (clientType.equalsIgnoreCase(ClientType.RECEIVER.toString())) {
                        this.clientReceiverSetUp();

                    }
                }
            }
            String readyMessage = "y";
            System.out.println("FILE READY... SEND [y]/n");
            Scanner scanner = new Scanner(System.in);
            if(scanner.nextLine().equalsIgnoreCase("n")) {
                // TODO do something
            } else {
                // TODO send file
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void clientEmitterSetUp() throws IOException {

            System.out.println("Client is a emitter");

            int fileNameLength = dataInputStream.readInt();
            byte[] fileNameBytes = new byte[fileNameLength];
            dataInputStream.readFully(fileNameBytes, 0, fileNameLength);
            fileName = new String(fileNameBytes);

            int fileLength = dataInputStream.readInt();
            fileBytes = new byte[fileLength];
            dataInputStream.readFully(fileBytes, 0, fileLength);



            System.out.printf("THE FILE NAME IS %s%n", fileName);

    }

    private void clientReceiverSetUp() {


        Runnable clientReceiver = () -> {
            System.out.println("Client is receiver");





        };


    }
}
