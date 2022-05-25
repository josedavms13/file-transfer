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

    protected BufferedReader in;
    protected BufferedWriter out;

    public Client (String host, int port) throws IOException {
        System.out.println(port);
        this.socket = new Socket(host, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        in = new BufferedReader(new InputStreamReader(dataInputStream));
        out = new BufferedWriter(new OutputStreamWriter(dataOutputStream));
    }

    protected void closeEverything(){

    }




}

