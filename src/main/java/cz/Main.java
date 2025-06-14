package cz;
import cz.Character;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.printBoard(game.board);
        // Example moves
        game.move(0, 0, Character.X);
        game.move(1, 1, Character.O);
        game.move(2, 2, Character.X);
        game.printBoard(game.board);

        // Check if X is the winner
        if (game.checkWinner(Character.X)) {
            System.out.println("X is the winner!");
        } else {
            System.out.println("No winner yet.");
        }
    }}