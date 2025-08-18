import java.util.ArrayList;

public class PlayerShips {
    private ArrayList<Ship> allShips;

    public PlayerShips() {
        allShips = new ArrayList<Ship>();
    }

    public void addShips() {
        Ship carrier = new Ship("Carrier", 5);
        Ship battleship = new Ship("Battleship", 4);
        Ship cruiser = new Ship("Cruiser", 3);
        Ship submarine = new Ship("Submarine", 3);
        Ship destroyer = new Ship("Destroyer", 2);

        allShips.add(carrier);
        allShips.add(battleship);
        allShips.add(cruiser);
        allShips.add(submarine);
        allShips.add(destroyer);
    }

    public Ship getShip(int index) {
        if (index < 0 || index > allShips.size()) {
            System.err.println("Invalid index to get ship. Try again!");
            return null;
        }

        return allShips.get(index);
    }

    public boolean placeShip(char[][] gameBoard, Ship ship, int row, 
    int col, boolean horizontal) {
        boolean status = false;
        boolean verifyPos = ship.verifyPlacement(gameBoard, row,
             col, horizontal);

        if (verifyPos) {
            ship.setBoard(gameBoard, row, col, horizontal);
            status = true;
        } else {
            return status;
        }

        return status;
    }
}
