package main.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    private ServerSocket serverSocket;

    private ArrayList<ConnectionHandler> connections;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;

        try {
            serverSocket = new ServerSocket(8073);


        } catch (IOException e) {
            throw new RuntimeException(e);

            //TODO shutdown server
        }
    }


}
