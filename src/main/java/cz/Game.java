package cz;

public class Game {
    Character[][] board = new Character[3][3];
    public boolean move ( int x, int y, Character character){
        if (x < 0 || x >= 3 || y < 0 || y >= 3) {
            return false; // Invalid move
        }
        if (board[x][y] != Character.EMPTY) {
            return false; // Cell already occupied
        }
        board[x][y] = character;
        return true; // Move successful
    }
    public boolean checkWinner (Character character){
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == character && board[i][1] == character && board[i][2] == character) ||
                    (board[0][i] == character && board[1][i] == character && board[2][i] == character)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == character && board[1][1] == character && board[2][2] == character) ||
                (board[0][2] == character && board[1][1] == character && board[2][0] == character)) {
            return true;
        } else {
            return false; // No winner}}
        }
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
    public void printBoard(Character[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
