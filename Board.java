/**
 * Joshua Wong
 * Summer 2025
 * Game.java
 */
import java.util.Scanner;


/**
 * Game class is where all the data about the game is currently running and
 * contains all of the user information and status of the game.
 */
public class Board {

    private char[][] gameBoard;

    /**
     * Game constructor is where the game board is created to start setting
     * up the game.
     */
    public Board() {
        gameBoard = new char[10][10];
    }

    public int boardRow() {
        return gameBoard.length;
    }

    public int boardCol() {
        return gameBoard[0].length;
    }

    /**
     * The initBoard() method sets up the game by adding water to the board
     * so that the user can add specific ships when starting the game.
     */
    public void initBoard() {
        for (int r = 0; r < gameBoard.length; r++) {
            for (int c = 0; c < gameBoard[r].length; c++) {
                gameBoard[r][c] = '~';
            }
        }
    }

    /**
     * The printBorder() method prints a border to cover the game board and
     * look more visually appealing when printed onto the terminal.
     * @param size controls the size of how big the border should be for
     * the board
     */
    private void printBorder(int size) {
        // Prints a border depending on the size
        // such as size = 5 for =====
        for (int i = 0; i < size; i++) {
            System.out.print("=");
        }

        // To prevent any more modifications onto the border
        System.out.println();
    }

    /**
     * The printBoard() method prints the ENTIRE board to update when
     * playing Battleship.
     */
    private void printBoard() {
        // To retrieve letter when printing out each row on the board
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};

        // To print the top border of the board
        int borderSize = 43;
        printBorder(borderSize);

        // To print the column numbers on the board
        for (int i = 0; i < gameBoard.length; i++) {
            System.out.print("   " + i);
        }
        System.out.println();

        // To print the main data of the board along with the letter
        for (int r = 0; r < gameBoard.length; r++) {
            // To print the letter first
            StringBuilder line = new StringBuilder();
            line.append(letters[r]);

            // To go through each data of the board for that row
            for (int c = 0; c < gameBoard[r].length; c++) {
                // To print out the data in the board
                line.append(" | ");
                line.append(gameBoard[r][c]);

                // To prevent having duplicate separations at the end
                if (c == gameBoard[r].length - 1) {
                    line.append(" |");
                }
            }

            // To print the row in full and go to the next row
            System.out.println(line);
        }

        // To print the bottom border of the board to finish visualization
        printBorder(borderSize);
    }

    /**
     * 
     */
    public int placeShips(Player player, Scanner userInput) {
        int result = -1;

        player.addShips();

        int curr = 0;
        int remainingShips = 5;
        do {
            System.out.print("Which row do you want to place your ship? (A-J): ");
            char rowInput = Character.toUpperCase(userInput.next().charAt(0));

            int row = rowInput - 'A';

            if (row < 0 || row > gameBoard.length) {
                System.err.println("Invalid row. Please try again.");
            } else {
                System.out.print("Which col do you want to place your ship? (0-9): ");
                int col = userInput.nextInt();

                if (col < 0 || col > gameBoard.length) {
                    System.err.println("Invalid col. Please try again.");
                } else {
                    System.out.print("Do you want to place ship horizontal (Y) or vertical (N)? ");
                    char horizontalInput = Character.toUpperCase(
                        userInput.next().charAt(0)
                    );

                    boolean horizontal = false;

                    if (horizontalInput == 'Y') {
                        horizontal = true;
                    }

                    boolean status = player.placeShip(gameBoard, player.getShip(curr), 
                    row, col, horizontal);

                    if (!status) {
                        System.out.println("Sorry, not valid area. Try again.");
                    } else {
                        curr++;
                        printBoard();
                    }
                }
            }
        } while (curr < remainingShips);

        result = 0;

        return result;
    }

    public void attackShip(PlayerShips player, PlayerShips target, int row,
    int col) {
        if (row < 0 || row > gameBoard.length 
        || col < 0 || col > gameBoard.length) {
            System.err.println("Invalid row or col position. Try again!");
            return;
        }

        if (gameBoard[row][col] == 'X' || gameBoard[row][col] == 'O') {
            System.out.println("Coordinate is already attacked. Please choose another area.");
        } else if (gameBoard[row][col] == '~') {
            player.announceMiss();
            gameBoard[row][col] = 'O';
        } else {
            player.announceHit(target);
            gameBoard[row][col] = 'X';
            target.verifyDestroyed(row, col);
        }

        printBoard();
    }
}