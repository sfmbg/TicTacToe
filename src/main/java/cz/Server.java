package cz;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    ServerSocket server = new ServerSocket(8080);
    Socket socket = server.accept();

    public Server() throws IOException {
        System.out.println("Server started and waiting for client connection...");
        System.out.println("Client connected from: " + socket.getInetAddress() + ":" + socket.getPort());
    }
}
