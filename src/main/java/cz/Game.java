package cz;

import java.util.Scanner;

public class Game {
    private Scanner sc = new Scanner(System.in);
    private Character[][] board = new Character[3][3];
    public int lastX = -1;
    public int lastY = -1;


    public Game() {
        initializeEmptyBoard();
    }

    private void initializeEmptyBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Character.EMPTY;
            }
        }
    }

    public boolean makeMove (Character character){
        System.out.println("Enter your move (x,y): ");
        String input = sc.nextLine();
        int[] coordinates = parseInputToCoordinates(input);
        int x = coordinates[0];
        int y = coordinates[1];
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            return false; // Invalid move
        }
        if (board[x][y] != Character.EMPTY) {
            return false; // Cell already occupied
        }
        board[x][y] = character;

        lastX = x;
        lastY = y;
        return true; // Move successful
    }

    public Character[][] getBoard() {
        return board;
    }

    public int[] parseInputToCoordinates(String input) {
        String[] parts = input.split(",");
        if (parts.length != 2) {
            return null; // Invalid input format
        }
        int x = Integer.parseInt(parts[0].trim());
        int y = Integer.parseInt(parts[1].trim());
        return new int[]{x, y};
    }

    public boolean checkWinner(Character c) {
        // Lines
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == c && board[i][1] == c && board[i][2] == c) ||
                    (board[0][i] == c && board[1][i] == c && board[2][i] == c)) {
                System.out.println("Winner: " + c);
                return true;
            }
        }
        // Diagonals
        if ((board[0][0] == c && board[1][1] == c && board[2][2] == c) ||
                (board[0][2] == c && board[1][1] == c && board[2][0] == c)) {
            System.out.println("Winner: " + c);
            return true;
        }

        return false;
    }
    public boolean isDraw() {
        for (Character[] row : board) {
            for (Character cell : row) {
                if (cell == Character.EMPTY) {
                    return false;
                }
            }
        }
        System.out.println("It's a draw!");
        return true;
    }

    public boolean isYourTurn(
            Character character) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == Character.X) {
                    countX++;
                } else if (board[i][j] == Character.O) {
                    countO++;
                }
            }
        }
        return (character == Character.X) == (countX <= countO);
    }

    public void printBoard() {
        System.out.println("Currently:");
        for (Character[] row : board) {
            for (Character cell : row) {
                switch (cell) {
                    case X -> System.out.print(" X ");
                    case O -> System.out.print(" O ");
                    case EMPTY -> System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }


}

