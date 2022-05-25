package main.server.clients;

import enums.ClientType;
import enums.StatusCode;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;

public class Emitter extends ServerClient{

    private String fileName;

    private byte[] fileBytes;
    private int fileLength;


    public Emitter(Socket socket) throws IOException {
        super(socket);

        dataInputStream = new DataInputStream(socket.getInputStream());
        dataOutputStream = new DataOutputStream(socket.getOutputStream());

        setUp();

    }

    private void setUp() throws IOException {
        int fileNameLength = dataInputStream.readInt();
        byte[] fileNameBytes = new byte[fileNameLength];
        dataInputStream.readFully(fileNameBytes, 0, fileNameLength);
        fileName = new String(fileNameBytes);

        fileLength = dataInputStream.readInt();
        fileBytes = new byte[fileLength];
        dataInputStream.readFully(fileBytes, 0, fileLength);
    }

    public void sendStatusCode(StatusCode statusCode) throws IOException {
        dataOutputStream.writeInt(statusCode.code);
    }

    public int getStatusCode() throws IOException {
        return dataInputStream.readInt();
    }
    public String getFileName() {
        return fileName;
    }

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public int getFileLength() {
        return fileLength;
    }
}
