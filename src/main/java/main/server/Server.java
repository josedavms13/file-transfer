package main.server;

import enums.ClientType;
import enums.StatusCode;
import main.server.clients.Emitter;

import javax.sound.midi.Receiver;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
                        this.clientReceiverSetUp();

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
                            emitter.sendStatusCode(StatusCode.DIDNT_SEND);
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }).start();
            }

    }

    private void clientReceiverSetUp() {


        Runnable clientReceiver = () -> {
            System.out.println("Client is receiver");





        };


    }
}
