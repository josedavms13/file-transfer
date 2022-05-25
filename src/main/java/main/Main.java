package main;

import enums.ClientType;
import main.client.emitter.CliEmitter;
import main.client.emitter.CliReceiver;
import main.server.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Arrays;

public class Main {

    private static final int PORT = 4281;

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));

        switch (args[0]) {
            case "-s" -> {
                System.out.println("Server running on port "+ PORT);
                Server server = new Server(PORT);
            }
            case "-e" -> {
                System.out.println("Sat up as CLI EMITTER");
                CliEmitter client = new CliEmitter(args[1], args[2], PORT);
            }
            case "-r" -> {
                CliReceiver cliReceiver = new CliReceiver(args[0], PORT);
            }
            default -> System.out.println("Missing arguments");
        }
    }
}
