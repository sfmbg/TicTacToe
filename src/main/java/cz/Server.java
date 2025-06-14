package cz;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Server {
    ServerSocket server = new ServerSocket(8080);
    Socket socket = server.accept();
}
