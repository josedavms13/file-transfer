package main;

import enums.ClientType;
import main.client.emitter.CliEmitter;
import main.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Main {

    private static final int PORT = 4281;

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));

        switch(args[0]) {
            case "-s" :
                System.out.println("Server");
                Server server = new Server(PORT);
                break;

            case "-e":

                System.out.println("cli emitter");
                CliEmitter client = new CliEmitter(args[1], args[2], PORT);


            break;

            case "-r":

                System.out.println("cli receiver");

            break;

            default :
                System.out.println("Missing arguments");
        }
    }
}
