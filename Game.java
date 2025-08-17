/**
 * Joshua Wong
 * Summer 2025
 * Game.java
 */

/**
 * Game class is where all the data about the game is currently running and
 * contains all of the user information and status of the game.
 */
public class Game {

    private char[][] gameBoard;

    /**
     * Game constructor is where the game board is created to start setting
     * up the game.
     */
    public Game() {
        gameBoard = new char[10][10];
    }

    /**
     * The initBoard() method sets up the game by adding water to the board
     * so that the user can add specific ships when starting the game.
     */
    private void initBoard() {
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
     * The main() method runs the Battleship game in full and 
     * allow the user to setup their information.
     * @param args contains all the added standard input from the user 
     * (but it's not necessary)
     */
    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        System.out.println("Each person take turns firing at each other's ship. Last one standing wins!");

        Game mainGame = new Game();

        mainGame.initBoard();
        mainGame.printBoard();
    }
}