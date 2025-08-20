import java.util.ArrayList;

public class PlayerShips {
    private String playerName;
    private ArrayList<Ship> allShips;
    private int numOfShips;


    public PlayerShips(String name) {
        playerName = name;
        allShips = new ArrayList<Ship>();
        numOfShips = 5;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void announceMiss() {
        System.out.println(playerName + " did not hit target.");
    }

    public void announceHit(PlayerShips target) {
        System.out.println(playerName + " hit " 
        + target.playerName + "'s ship.");
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

    public void verifyDestroyed(int row, int col) {

        for (int i = allShips.size() - 1; i >= 0; i--) {

            if (allShips.get(i).containsCoordinate(row, col) && 
            allShips.get(i).cellsLeft() == 0) {
                System.out.println("Target's " + allShips.get(i).getName() 
                + " has been destroyed!");
                allShips.remove(allShips.get(i));
                numOfShips--;
            }

        }
        
    }

    public int remainingShips() {
        return numOfShips;
    }
}
