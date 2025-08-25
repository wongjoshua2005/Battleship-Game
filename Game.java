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

        if (!player2Name.equals("Bot")) {
            player2 = new Player(player2Name); 
        } else {
            player2 = new Bot("Bot Player", player2Board);
        }

        player2.addShips();
    }

    private void makeMove(Board targetBoard, 
    Player player, Player target, Scanner userInput, boolean botTurn) {
        System.out.println(player.getPlayerName() + "\'s move!");

        if (botTurn) {
            Bot bot = (Bot) player;
            bot.botMove(player, target);
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


                    Bot botPlayer = (Bot) player2;
                    botPlayer.randomShips();
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
            if (player1.remainingShips() == 0 && player2.remainingShips() != 0) {
                System.out.println("Game over! " + player2.getPlayerName() + 
                " win!");
                running = false;
            } else {
                switch (mode) {
                    case 1:
                        makeMove(player2Board, player1, player2, 
                        userInput, true);
                        break;
                    case 2:
                        makeMove(player2Board, player1, player2, 
                        userInput, true);
                        break;
                    case 3:
                        makeMove(player2Board, player1, player2, 
                        userInput, false);
                        break;
                }
            }

            if (player2.remainingShips() == 0 && player1.remainingShips() != 0) {
                System.out.println("Game over! " + player1.getPlayerName() + 
                " win!");
                running = false;
            } else {
                switch (mode) {
                    case 1:
                        makeMove(player1Board, player2, player1, 
                        userInput, true);
                        break;
                    case 2:
                        makeMove(player1Board, player2, player1, 
                        userInput, true);
                        break;
                    case 3:
                        makeMove(player1Board, player2, player1, 
                        userInput, false);
                        break;
                }
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
                System.out.print("Enter player name: ");
                String playerName = scan.next();


                mainGame = new Game(playerName, "Bot");

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
