package main.server.clients;

import enums.StatusCode;

import java.io.*;
import java.net.Socket;

public class Receiver extends ServerClient{

    private boolean isReadyToReceive = false;

    public Receiver (Socket socket) throws IOException {
        super(socket);

        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        in = new BufferedReader(new InputStreamReader(dataInputStream));
        out = new BufferedWriter(new OutputStreamWriter(dataOutputStream));

        setUp();
    }



    private void setUp() throws IOException {

        out.write("Drop the target folder here");

        int status = dataInputStream.readInt();

        if (status == StatusCode.READY_TO_RECEIVE.code) {
            isReadyToReceive = true;
        }

    }

    private void sendFile(byte[] data) throws IOException {
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        int statusCode = dataInputStream.readInt();
        if(statusCode == StatusCode.RECEIVED.code) {
            System.out.println("File sent to CLI receiver correctly");
        } else {
            System.out.println("Client couldn't receive the file");
            System.out.println(getStatusCode(statusCode));
        }
    }

}
