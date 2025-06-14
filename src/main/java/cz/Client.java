package cz;

import java.io.IOException;
import java.net.Socket;

public class Client {
    Socket socket = new Socket("localhost", 8080);

    public Client() throws IOException {
        System.out.println("Connected to server at localhost:8080");
    }
}
