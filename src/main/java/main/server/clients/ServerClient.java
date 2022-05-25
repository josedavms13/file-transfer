package main.server.clients;

import java.io.*;
import java.net.Socket;

public class ServerClient {

    protected DataOutputStream dataOutputStream;
    protected DataInputStream dataInputStream;

    protected BufferedReader in;
    protected BufferedWriter out;

    protected Socket socket;

    public ServerClient(Socket socket) {
        this.socket = socket;
    }

    protected void closeConnection() throws IOException {
        this.socket.close();
    }
}
