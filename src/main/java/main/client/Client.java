package main.client;

import java.io.*;
import java.net.Socket;

public class Client   {

    protected Socket socket;
    protected DataOutputStream dataOutputStream;
    protected DataInputStream dataInputStream;

    protected BufferedReader in;
    protected BufferedWriter out;
    protected File file;

    public Client (String host, String path, int port) throws IOException {
        this.socket = new Socket(host, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        in = new BufferedReader(new InputStreamReader(dataInputStream));
        out = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
        file = new File(path);
    }

    protected void closeEverything(){

    }




}

