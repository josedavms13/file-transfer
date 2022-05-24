package main.server.clients;

import java.io.File;
import java.net.Socket;

public class Emitter extends ServerClient{

    private Socket socket;
    private String fileName;

    public Emitter(Socket socket, String fileName, File file) {
        this.socket = socket;
        this.fileName = fileName;
        this.file = file;
    }

    private File file;

    public Emitter(Socket socket) {
        this.socket = socket;
    }


}
