package main.server.clients;

import enums.StatusCode;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class ServerClient {

    protected DataOutputStream dataOutputStream;
    protected DataInputStream dataInputStream;
    protected FileOutputStream fileOutputStream;
    protected FileInputStream fileInputStream;


    protected Socket socket;

    public ServerClient(Socket socket) {
        this.socket = socket;
    }

    public void closeConnection() throws IOException {
        this.socket.close();
    }

    protected StatusCode getStatusCode(int statusCode) {
        return Arrays.stream(StatusCode.values()).filter(stCode -> stCode.code == statusCode).findAny().get();
    }
}
