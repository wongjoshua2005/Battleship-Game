import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Board player1Board;
    private Board player2Board;

    public Game(String player1Name, String player2Name) {
        player1Board = new Board();
        player1Board.initBoard();

        player1 = new Player(player1Name);
        player1.addShips();

        player2Board = new Board();
        player2Board.initBoard();

        player2 = new Player(player2Name);
        player2.addShips();
    }

    private void makeMove(Board targetBoard, 
    Player player, Player target, Scanner userInput) {
        System.out.println(player.getPlayerName() + "\'s move!");
        System.out.print("Enter the row to attack: ");

        char rowInput = Character.toUpperCase(userInput.next().charAt(0));
        int row = rowInput - 'A';

        if (row < 0 || row > targetBoard.boardLength()) {
            System.err.println("Invalid row. Please try again.");
        } else {
            System.out.print("Enter the col to attack: ");
            int col = userInput.nextInt();

            if (col < 0 || col > targetBoard.boardLength()) {
                System.err.println("Invalid col. Please try again.");
            } else {
                targetBoard.attackShip(player, target, row, col);
            }
        }
    }

    private void runGame(Scanner userInput) {
        boolean settingGame = true;
        boolean running = false;

        do {
            System.out.println("Player 1, please place your ships down.");
            player1Board.placeShips(player1, userInput);

            System.out.println("Player 2, please place your ships down.");
            player2Board.placeShips(player2, userInput);

            settingGame = false;
        } while (settingGame);

        System.out.println("The game will start now. May the best win!");

        running = true;

        while (running) {
            if (player1.remainingShips() == 0 && player2.remainingShips() != 0) {
                System.out.println("Game over! " + player2.getPlayerName() + 
                " win!");
                running = false;
            } else {
                //System.out.println("Player 1 remaining ships: " + player1.remainingShips());

                makeMove(player2Board, player1, player2, userInput);
            }

            if (player2.remainingShips() == 0 && player1.remainingShips() != 0) {
                System.out.println("Game over! " + player1.getPlayerName() + 
                " win!");
                running = false;
            } else {
                //System.out.println("Player 2 remaining ships: " + player2.remainingShips());

                makeMove(player1Board, player2, player1, userInput);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Battleship!");
        System.out.println("Each person takes a turn" + 
        " firing at each other's ship. " + "Last one standing wins!");

        Scanner scan = new Scanner(System.in);

        System.out.print("Please select a game mode. Do you want " + 
        "bot vs bot (1), bot vs human (2), or human vs human (3): ");
        int mode = scan.nextInt();

        Game mainGame = new Game("Player1", "Player2");

        switch (mode) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                System.out.print("Enter player 1's name: ");
                String player1Name = scan.next();

                System.out.print("Enter player 2's name: ");
                String player2Name = scan.next();

                mainGame = new Game(player1Name, player2Name);
                break;
            default:
                System.err.println("Sorry, that is not a valid option. " + 
                "Please try again!");
                break;
        }

        mainGame.runGame(scan);
    }
}
