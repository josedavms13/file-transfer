package main.client.emitter;

import enums.ClientType;
import main.client.Client;

import java.io.FileInputStream;

public class CliEmitter extends Client {


    private FileInputStream fileInputStream;

    public CliEmitter(ClientType clientType, String host, String port, String filePath) {
        super(clientType, host, Integer.parseInt(port), filePath);

        this.host = host;
        this.port = Integer.parseInt(port);


        System.out.println(filePath);
        System.out.println(host);
        System.out.println(port);
        System.out.println("wait");
    }


    @Override
    protected void executeClientTypeAction() {

    }
}
