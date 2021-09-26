
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerTwoField extends GameField {
    protected static char[][] field = new char[10][10];
    protected static char[][] fogBoard = new char[10][10];

    public PlayerTwoField() {
        for (char[] cell: field) {
            Arrays.fill(cell, '~');
        }
        for (char[] cell: fogBoard) {
            Arrays.fill(cell, '~');
        }
    }

    @Override
    protected void displayGameField() {
        char rowID = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] row: field) {
            System.out.print(rowID + " ");
            rowID++;
            for (char cell: row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    @Override
    protected boolean checkGameField(int[] c1, int[] c2) {
        for (int i = Math.max(0, c1[0] - 1); i <= Math.min(9, c2[0] + 1); i++) {
            for (int j = Math.max(0, c1[1] - 1); j <= Math.min(9, c2[1] + 1); j++) {
                if (field[i][j] != '~') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void placeShip(int[] c1, int[] c2) {
        for (int i = c1[0]; i <= c2[0]; i++) {
            for (int j = c1[1]; j <= c2[1]; j++) {
                field[i][j] = 'O';
            }
        }

    }

    @Override
    protected int isShipHit(String shot) {
        int A1 = shot.charAt(0) - 'A';
        int A2 = 0;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(shot);
        while (m.find()) {
            A2 = Integer.parseInt(m.group()) - 1;
        }
        for (int i = Math.max(0, A1); i <= 9; i++) {
            for (int j = Math.max(0, A2); j <= 9; j++) {
                if (PlayerOneField.field[i][j] == 'O') {
                    PlayerOneField.field[i][j] = 'X';
                    fogBoard[i][j] = 'X';
                    return 1;
                } else if (PlayerOneField.field[i][j] == 'X') {
                    PlayerOneField.field[i][j] = 'X';
                    fogBoard[i][j] = 'X';
                    return 2;
                } else if (PlayerOneField.field[i][j] == '~') {
                    PlayerOneField.field[i][j] = 'M';
                    fogBoard[i][j] = 'M';
                    return 0;
                } else if (PlayerOneField.field[i][j] == 'M') {
                    PlayerOneField.field[i][j] = 'M';
                    fogBoard[i][j] = 'M';
                    return 3;
                }
            }
        }
        return 0;
    }

    @Override
    protected void displayFogBoard() {
        char rowID = 'A';
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (char[] row: fogBoard) {
            System.out.print(rowID + " ");
            rowID++;
            for (char cell: row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    @Override
    protected boolean allShipSank() {

        return ship1_Sank() && ship2_Sank() && ship3_Sank() && ship4_Sank() && ship5_Sank();
    }

    @Override
    protected boolean ship1_Sank() {
        int[] c3 = Ship.AIRCRAFT_CARRIER.getC3();
        int[] c4 = Ship.AIRCRAFT_CARRIER.getC4();
        int counter = 0;
        for (int i = c3[0]; i <= c4[0]; i++) {
            for (int j = c3[1]; j <= c4[1]; j++) {
                if (field[i][j] == 'X') {
                    counter++;
                }
            }
        }

        return counter == 5;
    }

    @Override
    protected boolean ship2_Sank() {
        int[] c3 = Ship.BATTLESHIP.getC3();
        int[] c4 = Ship.BATTLESHIP.getC4();
        int counter = 0;
        for (int i = c3[0]; i <= c4[0]; i++) {
            for (int j = c3[1]; j <= c4[1]; j++) {
                if (field[i][j] == 'X') {
                    counter++;
                }
            }
        }

        return counter == 4;
    }

    @Override
    protected boolean ship3_Sank() {
        int[] c3 = Ship.SUBMARINE.getC3();
        int[] c4 = Ship.SUBMARINE.getC4();
        int counter = 0;
        for (int i = c3[0]; i <= c4[0]; i++) {
            for (int j = c3[1]; j <= c4[1]; j++) {
                if (field[i][j] == 'X') {
                    counter++;
                }
            }
        }

        return counter == 3;
    }

    @Override
    protected boolean ship4_Sank() {
        int[] c3 = Ship.CRUISER.getC3();
        int[] c4 = Ship.CRUISER.getC4();
        int counter = 0;
        for (int i = c3[0]; i <= c4[0]; i++) {
            for (int j = c3[1]; j <= c4[1]; j++) {
                if (field[i][j] == 'X') {
                    counter++;
                }
            }
        }

        return counter == 3;
    }

    @Override
    protected boolean ship5_Sank() {
        int[] c3 = Ship.DESTROYER.getC3();
        int[] c4 = Ship.DESTROYER.getC4();
        int counter = 0;
        for (int i = c3[0]; i <= c4[0]; i++) {
            for (int j = c3[1]; j <= c4[1]; j++) {
                if (field[i][j] == 'X') {
                    counter++;
                }
            }
        }
        return counter == 2;
    }
}
