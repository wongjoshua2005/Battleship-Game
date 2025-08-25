import java.util.Scanner;

public class Game {
    private Player player1;
    private Player player2;
    private Bot bot1;
    private Bot bot2;
    private Board player1Board;
    private Board player2Board;

    public Game() {
        player1Board = new Board();
        player1Board.initBoard();

        bot1 = new Bot("Bot1", player1Board, player2Board);
        bot1.addShips();

        player2Board = new Board();
        player2Board.initBoard();

        bot2 = new Bot("Bot2", player2Board, player1Board);
        bot2.addShips();  
    }

    public Game(String player) {
        player1Board = new Board();
        player1Board.initBoard();

        player1 = new Player(player);
        player1.addShips();

        player2Board = new Board();
        player2Board.initBoard();

        bot2 = new Bot("Bot", player2Board, player1Board);
        bot2.addShips();
    }

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
    Player player, Player target, Scanner userInput, boolean botTurn) {
        System.out.println(player.getPlayerName() + "\'s move!");

        if (botTurn) {
            Bot bot = (Bot) player;
            bot.botMove(player, target, targetBoard);
        } else {
            System.out.print("Enter the row to attack: ");

            char rowInput = Character.toUpperCase(userInput.next().charAt(0));
            int row = rowInput - 'A';

            if (row < 0 || row > targetBoard.boardRow()) {
                System.err.println("Invalid row. Please try again.");
            } else {
                System.out.print("Enter the col to attack: ");
                int col = userInput.nextInt();

                if (col < 0 || col > targetBoard.boardRow()) {
                    System.err.println("Invalid col. Please try again.");
                } else {
                    targetBoard.attackShip(player, target, row, col);
                }
            }
        }

        player1Board.printBoard();
        player2Board.printBoard();
    }

    private void runGame(Scanner userInput, int mode) {
        boolean settingGame = true;
        boolean running = false;

        do {
            switch (mode) {
                case 1:
                    break;
                case 2:
                    System.out.println("Player, please place your ships down.");
                    player1Board.placeShips(player1, userInput);

                    bot2.randomShips();
                    break;
                case 3:
                    System.out.println("Player 1, please place your ships down.");
                    player1Board.placeShips(player1, userInput);

                    System.out.println("Player 2, please place your ships down.");
                    player2Board.placeShips(player2, userInput);
                    break;
            }

            settingGame = false;
        } while (settingGame);

        System.out.println("The game will start now. May the best win!");

        running = true;

        while (running) {
            switch (mode) {
                case 1:
                    break;
                case 2:
                    if (player1.remainingShips() == 0 
                    && bot2.remainingShips() != 0) {
                        System.out.println("Game over! The bot wins!");
                        running = false;             
                    } else {
                        makeMove(player2Board, player1, (Player) bot2, 
                        userInput, false);
                    }

                    if (bot2.remainingShips() == 0 
                    && player1.remainingShips() != 0) {
                        System.out.println("Game over! " + player1.getPlayerName() + " win!");
                        running = false;
                    } else {
                        makeMove(player1Board, (Player) bot2, player1, 
                        userInput, true);
                    }
                    break;
                case 3:
                    if (player1.remainingShips() == 0 
                    && player2.remainingShips() != 0) {
                        System.out.println("Game over! " + player2.getPlayerName() + 
                        " win!");
                        running = false;             
                    } else {
                        makeMove(player2Board, player1, player2, 
                        userInput, false);
                    }

                    if (player2.remainingShips() == 0 
                    && player1.remainingShips() != 0) {
                        System.out.println("Game over! " + player1.getPlayerName() + " win!");
                        running = false;
                    } else {
                        makeMove(player1Board, player2, player1, 
                        userInput, false);
                    }
                    break;
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

        Game mainGame = null;

        switch (mode) {
            case 1:
                break;
            case 2:
                System.out.print("Enter player name: ");
                String playerName = scan.next();

                mainGame = new Game(playerName);
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

        mainGame.runGame(scan, mode);
    }
}
