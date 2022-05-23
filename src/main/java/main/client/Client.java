package main.client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;

public class Client   {

    protected Socket socket;
    protected DataOutputStream dataOutputStream;
    protected File file;

    public Client (String host, String path) throws IOException {
        int port = 4081;
        this.socket = new Socket(host, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        file = new File(path);
    }




}

