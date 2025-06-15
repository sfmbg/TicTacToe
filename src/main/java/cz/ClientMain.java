package cz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        Socket socket = new Socket("localhost", 8080);
        System.out.println("Connected to server!");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        Character myChar = Character.O;
        Character opponentChar = Character.X;

        while (true) {
            // Wait for opponent
            System.out.println("Waiting for opponent...");
            game.printBoard();
            String input = in.readLine();
            applyMoveFromString(game, input, opponentChar);
            game.printBoard();

            if (game.checkWinner(opponentChar) || game.isDraw()) break;

            // My turn
            while (!game.makeMove(myChar)) {
                System.out.println("Try again.");
            }
//            game.printBoard();
            out.println(gameToLastMove(game));

            if (game.checkWinner(myChar) || game.isDraw()) break;
        }

        socket.close();
    }

    private static String gameToLastMove(Game game) {
        return game.lastX + "," + game.lastY;
    }


    private static void applyMoveFromString(Game game, String move, Character c) {
        int[] xy = game.parseInputToCoordinates(move);
        if (xy != null) {
            game.getBoard()[xy[0]][xy[1]] = c;
        }
    }

}
