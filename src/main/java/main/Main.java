package main;

import main.client.emitter.CliEmitter;
import main.client.emitter.CliReceiver;
import main.server.Server;

import java.io.IOException;
import java.util.Arrays;

public class Main {

    private static final int PORT = 4281;

    public static void main(String[] args) throws IOException {
        System.out.println(Arrays.toString(args));

        switch (args[0]) {
            case "-s":
                System.out.println("Server running on port "+ PORT);
                Server server = new Server(PORT);
                break;
            case "-e" :
                System.out.println("Sat up as CLI EMITTER");
                CliEmitter client = new CliEmitter(args[1], args[2], PORT);
                break;
            case "-r" :
                CliReceiver cliReceiver = new CliReceiver(args[1], PORT);
                break;
            default :
                System.out.println("Missing arguments");
                break;
        }
    }
}
