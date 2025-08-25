import java.util.Stack;
import java.util.Random;

public class Bot extends Player {
    private String targetMode;
    private Board botBoard;
    private Stack<int[]> targetCells;
    private boolean[][] visited;

    public Bot(String name, Board gameBoard) {
        super(name);
        botBoard = gameBoard;
        targetCells = new Stack<int[]>();
        visited = new boolean[botBoard.boardRow()][botBoard.boardCol()];
        targetMode = "HUNT";
    }

    public void randomShips() {
        Random rand = new Random();

        int curr = 0;
        int remainingShips = 5;
        while (curr < remainingShips) {
            int randRow = rand.nextInt(botBoard.boardRow());
            int randCol = rand.nextInt(botBoard.boardCol());
            boolean randRotation = rand.nextBoolean();

            int result = 
            botBoard.placeShips(this, randRow, randCol, randRotation, curr);

            if (result == -1) {
                continue;
            } else {
                curr++;
            }
        }
    }

    private int[] nextMove() {
        Random rand = new Random();

        if (targetMode.equals("HUNT")) {
            // Pick random cell
            boolean searchCoordinates = true;
            int[] result = new int[2];

            do {
                int randRow = rand.nextInt(botBoard.boardRow());
                int randCol = rand.nextInt(botBoard.boardCol());

                if (!visited[randRow][randCol]) {
                    result[0] = randRow;
                    result[1] = randCol;
                    targetCells.push(result);

                    visited[randRow][randCol] = true;
                    searchCoordinates = false;
                }
            } while (searchCoordinates);

            return result;
        } else {
            return targetCells.pop();
        }
    }

    private void moveResult(int[] coordinates, String roundResult) {
        if (roundResult.equals("HIT")) {
            targetCells.add(new int[]{coordinates[0] + 1, coordinates[1]});
            targetCells.add(new int[]{coordinates[0] - 1, coordinates[1]});
            targetCells.add(new int[]{coordinates[0], coordinates[1] + 1});
            targetCells.add(new int[]{coordinates[0], coordinates[1] - 1});

            targetMode = "TARGET";
        } else if (roundResult.equals("SUNK")) {
            targetCells.clear();
            targetMode = "HUNT";
        }
    }

    public void botMove(Player bot, Player target) {
        int[] move = nextMove();

        String result = botBoard.attackShip(bot,
         target, move[0], move[1]);

        moveResult(move, result);
    }

    
}
