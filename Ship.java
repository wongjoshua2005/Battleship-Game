import java.util.ArrayList;
import java.util.HashMap;

public class Ship {
    private String name;
    private int numCells;
    //private HashMap<HashMap, String> cells;

    public Ship(String shipName, int cells) {
        name = shipName;
        numCells = cells;
        //cells = new HashMap<HashMap, String>();
    }

    public boolean verifyPlacement(char[][] gameBoard, int row, 
    int col, boolean horizontal) {
        boolean result = false;

        if (horizontal) {
            for (int i = 0; i < numCells; i++) {
                if (col + i >= gameBoard.length || col + i < 0 || 
                gameBoard[row][col + i] != '~') {
                    return result;
                }
            }
        } else {
            for (int i = 0; i < numCells; i++) {
                if (row + i >= gameBoard.length || row + i < 0 ||
                gameBoard[row + i][col] != '~') {
                    return result;
                }
            }
        }

        result = true;

        return result;
    }

    public void setBoard(char[][] gameBoard, int row, 
    int col, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < numCells; i++) {
                gameBoard[row][col + i] = 'S';
            }
        } else {
            for (int i = 0; i < numCells; i++) {
                gameBoard[row + i][col] = 'S';
            }
        }
    }
}
