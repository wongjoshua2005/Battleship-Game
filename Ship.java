import java.util.ArrayList;
import java.util.HashMap;

public class Ship {
    private String name;
    private int numCells;
    private HashMap<Integer, Integer> cells;

    public Ship(String shipName, int totalCells) {
        name = shipName;
        numCells = totalCells;
        cells = new HashMap<Integer, Integer>();
    }

    public int cellsLeft() {
        return cells.size();
    }

    public String getName() {
        return name;
    }

    public boolean containsCoordinate(int row, int col) {
        boolean result = false;

        if (cells.containsKey(row) && cells.containsValue(col)) {
            removeCell(row, col);
            result = true;
        }

        return result;
    }

    private void removeCell(int row, int col) {
        cells.remove(row, col);
        numCells--;
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
                cells.put(row, col + i);
            }
        } else {
            for (int i = 0; i < numCells; i++) {
                gameBoard[row + i][col] = 'S';
                cells.put(row + i, col);
            }
        }
    }
}
