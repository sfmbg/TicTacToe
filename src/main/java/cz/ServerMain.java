package cz;

import cz.Game;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) throws IOException {
        Game game = new Game();

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Waiting for opponent...");
        Socket socket = serverSocket.accept();
        System.out.println("Opponent connected.");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Character currentPlayer = Character.X;
        Character opponentPlayer = Character.O;

        while (true) {
            game.printBoard();
            while (!game.makeMove(currentPlayer)) {
                System.out.println("Invalid. Try again.");
            }
            game.printBoard();
            out.println(gameToLastMove(game)); // send last move

            if (game.checkWinner(currentPlayer) || game.isDraw()) break;

            // oppnent turn
            System.out.println("Waiting for opponent...");
            String input = in.readLine();
            applyMoveFromString(game, input, opponentPlayer);

            if (game.checkWinner(opponentPlayer) || game.isDraw()) {
                game.printBoard();
                break;
            }
        }

        socket.close();
        serverSocket.close();
    }

    private static String gameToLastMove(Game game) {
        Character[][] b = game.getBoard();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                if (b[x][y] != Character.EMPTY) {
                    if (x == lastX && y == lastY) {
                        return x + "," + y;
                    }
                }
            }
        }
        return "";
    }

    private static void applyMoveFromString(Game game, String move, Character c) {
        int[] xy = game.parseInputToCoordinates(move);
        if (xy != null) {
            game.getBoard()[xy[0]][xy[1]] = c;
        }
    }
    private static int lastX, lastY;

}
