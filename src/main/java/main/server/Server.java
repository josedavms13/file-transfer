package main.server;

import enums.ClientType;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    protected ClientType clientType;
    protected DataInputStream dataInputStream;
    public Server(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket clientSocket = serverSocket.accept();


        dataInputStream = new DataInputStream(clientSocket.getInputStream());

        //Client type
        int clientTypeLength = dataInputStream.readInt();
        if (clientTypeLength > 0) {
            byte[] clientTypeLengthBytes = new byte[clientTypeLength];
            dataInputStream.readFully(clientTypeLengthBytes, 0, clientTypeLength);
            String clientType = new String(clientTypeLengthBytes);
            System.out.println(clientType);
        }
    }


    @Override
    public void run() {

    }
}
