/**
 * Joshua Wong
 * Summer 2025
 * Game.java
 */

public class Game {
    private char[][] gameBoard;

    /**
     * 
     */
    public Game() {
        gameBoard = new char[10][10];
    }

    /**
     * 
     */
    private void createBoard() {
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                gameBoard[r][c] = '~';
            }
        }
    }

    private void printBorder(int size) {
        for (int i = 0; i < size; i++) {
            System.out.print("=");
        }
        System.out.println();
    }

    /**
     * 
     * @param args
     */
    private void printBoard() {
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        // Print top border
        int borderSize = 43;
        printBorder(borderSize);

        // Print main board
        for (int r = 0; r < gameBoard.length; r++) {
            StringBuilder line = new StringBuilder();
            line.append(letters[r]);

            for (int c = 0; c < gameBoard[r].length; c++) {
                line.append(" | ");
                line.append(gameBoard[r][c]);

                if (c == gameBoard[r].length - 1) {
                    line.append(" |");
                }
            }

            System.out.println(line);
        }

        // Print bottom border
        printBorder(borderSize);
    }

    /**
     * 
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");

        Game test = new Game();

        test.createBoard();
        test.printBoard();
    }
}