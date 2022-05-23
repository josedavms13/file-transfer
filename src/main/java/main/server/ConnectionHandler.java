package main.server;

import enums.ClientType;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ConnectionHandler {

    private Socket client;
    private InputStreamReader in;
    private PrintWriter out;

    private ClientType clientType;

    public ConnectionHandler(ServerSocket server) {

    }

    public ConnectionHandler (Socket client, ClientType clientType) {
        this.clientType = clientType;
        this.client = client;
    }

    public void run() {
        try {
            out = new PrintWriter(client.getOutputStream(), true);
            in = new InputStreamReader(client.getInputStream());

            out.println("Hello client");



        } catch (IOException e) {
            // TODO handle
        }
    }

}
