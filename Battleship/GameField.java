
public abstract class GameField {

    public GameField() {
    }

    protected abstract void displayGameField();

    protected abstract boolean checkGameField(int[] c1, int[] c2);

    protected abstract void placeShip(int[] c1, int[] c2);

    protected abstract int isShipHit(String shot);

    protected abstract void displayFogBoard();

    protected abstract boolean allShipSank();

    protected abstract boolean ship1_Sank();

    protected abstract boolean ship2_Sank();

    protected abstract boolean ship3_Sank();

    protected abstract boolean ship4_Sank();

    protected abstract boolean ship5_Sank();
}
