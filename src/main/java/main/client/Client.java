package main.client;

import enums.ClientType;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public abstract class Client   {

    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private ClientType clientType;
    private String filePath;
    protected String host;
    protected int port;
    protected Socket socket;

    public Client(ClientType clientType, String host, int port, String filePath) {
        this.port = port;
        this.host = host;
        this.clientType = clientType;
        try {
            connectionSetup();
            sendClientType();







        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    protected void connectionSetup() throws IOException {
        this.socket = new Socket(this.host, this.port);
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
    }

    protected File getFile(String filePath) throws IOException {
        return new File(filePath);
    }

    protected void sendClientType() throws IOException {
        dataOutputStream.writeChars(clientType.type);

    }





}

