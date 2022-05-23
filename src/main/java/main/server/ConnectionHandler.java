package main.server;

import enums.ClientType;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionHandler {
    protected ClientType clientType;
    protected DataInputStream dataInputStream;
    protected Socket client;

    public ConnectionHandler(Socket socket) throws IOException {



    }
}
