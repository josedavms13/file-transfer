package main;

import enums.ClientType;
import main.client.emitter.CliEmitter;
import main.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));

        switch(args[0]) {
            case "-s" :
                System.out.println("Server");
            break;

            case "-e":

                System.out.println("cli emitter");
                CliEmitter client = new CliEmitter(ClientType.EMMITER, args[1], args[2], args[3]);


            break;

            case "-r":

                System.out.println("cli receiver");

            break;

            default :
                System.out.println("Missing arguments");
        }
    }
}
