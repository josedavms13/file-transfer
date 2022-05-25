package main.server;

import com.google.common.io.Files;
import enums.ClientType;
import enums.StatusCode;
import main.server.clients.Emitter;
import main.server.clients.Receiver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;


    protected DataInputStream dataInputStream;
    protected DataOutputStream dataOutputStream;

    private Emitter emitter;
    private List<Receiver> receivers = new ArrayList<>();

    private String fileName;
    private byte[] fileBytes;
    boolean isServerAlsoReceiver;

    private boolean allClientsReady = false;
    private boolean isFileReadyToSend = false;
    private boolean isEmitterReady = false;


    public Server(int port) throws IOException {
        try {
            serverSocket = new ServerSocket(port);
            while (!allClientsReady) {
                clientSocket = serverSocket.accept();

                dataInputStream = new DataInputStream(clientSocket.getInputStream());
                //Client type
                int clientTypeLength = dataInputStream.readInt();
                if (clientTypeLength > 0) {
                    byte[] clientTypeLengthBytes = new byte[clientTypeLength];
                    dataInputStream.readFully(clientTypeLengthBytes, 0, clientTypeLength);
                    String clientType = new String(clientTypeLengthBytes);

                    if (clientType.equalsIgnoreCase(ClientType.EMITTER_CLI.toString())) {
                        this.clientEmitterSetUp(clientSocket);



                    } else if (clientType.equalsIgnoreCase(ClientType.RECEIVER.toString())) {
                        this.clientReceiverSetUp(clientSocket);

                    }
                }
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void clientEmitterSetUp(Socket socket) throws IOException {
            System.out.println("Client is a emitter");
            this.emitter = new Emitter(socket);

            if(emitter.getFileBytes().length > 0) {
                emitter.sendStatusCode(StatusCode.RECEIVED);
                isFileReadyToSend = true;
                new Thread(()-> {
                    try {
                        while (receivers.size() <= 0) {
                            int statusCode = emitter.getStatusCode();
                            System.out.println("Status Code: " + statusCode);


                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }

    }

    private void clientReceiverSetUp(Socket socket) throws IOException {
        System.out.println("A new receiver has arrived");
        new Thread(()-> {
            try {
                receivers.add(new Receiver(socket));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
