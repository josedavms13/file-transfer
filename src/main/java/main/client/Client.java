package main.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client   {

    protected Socket socket;
    protected DataOutputStream dataOutputStream;
    protected DataInputStream dataInputStream;

    public Client (String host, int port) throws IOException {
        System.out.println(port);
        System.out.println(host);
        this.socket = new Socket(host, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
    }

    protected void closeEverything(){

    }




}

