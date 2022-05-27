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

        setUp();
    }



    private void setUp() throws IOException {

        int status = dataInputStream.readInt();

        if (status == StatusCode.READY_TO_RECEIVE.code) {
            isReadyToReceive = true;
            System.out.println("Incoming receiver is ready");
        }
    }

    public StatusCode sendFile(String fileName, byte[] data) throws IOException {
        //Send file name
        dataOutputStream.writeInt(fileName.getBytes().length);
        dataOutputStream.write(fileName.getBytes());

        //Send file
        dataOutputStream.writeInt(data.length);
        dataOutputStream.write(data);
        StatusCode statusCode = getStatusCode(dataInputStream.readInt());
        if(statusCode.code == StatusCode.RECEIVED.code) {
            System.out.println("File sent to CLI receiver correctly");
        } else {
            System.out.println("Client couldn't receive the file");
            System.out.println(getStatusCode(statusCode.code));
        }
        return statusCode;
    }

}
