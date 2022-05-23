package main.client.emitter;

import enums.ClientType;
import main.client.Client;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CliEmitter extends Client {

    ClientType clientType = ClientType.EMMITER_CLI;

    public CliEmitter(String host, String path) throws IOException {
        super(host, path);

        sendClientTypeAndFile();
    }


    private void sendClientTypeAndFile() {

        // Get all content bytes

        //Client type
        byte[] clientTypeBytes = this.clientType.type.getBytes(StandardCharsets.UTF_8);

        // File Name
        String fileName = this.file.getName();
        byte[] fileNameBytes = fileName.getBytes(StandardCharsets.UTF_8);

        // File
        byte[] fileBytes = new byte[(int) this.file.length()];

        try {
            // Sending ClientType
            dataOutputStream.writeInt(clientTypeBytes.length);
            dataOutputStream.write(clientTypeBytes);

            // Sending File Name
            dataOutputStream.writeInt(fileNameBytes.length);
            dataOutputStream.write(fileBytes);

            // Sending File
            dataOutputStream.writeInt(fileBytes.length);
            dataOutputStream.write(fileBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
