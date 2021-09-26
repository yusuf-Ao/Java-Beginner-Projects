
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String ADD_SHIP = "Enter the coordinates of the %s (%d cells):\n";
    private static final String ERROR = "Error! Invalid coordinates, try again:\n";
    private static final String ERROR_1 = "Error! Wrong length of the %s! Try again:\n";
    private static final String ERROR_2 = "Error! Wrong ship location! Try again:\n";
    private static final String ERROR_4 = "Error! You entered the wrong coordinates! Try again:\n";

    static PlayerOneField playerOne = new PlayerOneField();
    static PlayerTwoField playerTwo = new PlayerTwoField();
    static GameField activePlayer = playerOne;
    static Ship[] ship = {Ship.AIRCRAFT_CARRIER, Ship.BATTLESHIP, Ship.SUBMARINE, Ship.CRUISER, Ship.DESTROYER};
    static int shipCounter = 0;
    static boolean p1_ship1_down = true;
    static boolean p1_ship2_down = true;
    static boolean p1_ship3_down = true;
    static boolean p1_ship4_down = true;
    static boolean p1_ship5_down = true;

    static boolean p2_ship1_down = true;
    static boolean p2_ship2_down = true;
    static boolean p2_ship3_down = true;
    static boolean p2_ship4_down = true;
    static boolean p2_ship5_down = true;


    public static void main(String[] args)  {
        setPlayerOne(playerOne);
        resetActivePlayer();
        setPlayerTwo(playerTwo);
        startGame();
    }

    private static void resetActivePlayer() {
        shipCounter = 0;
        if (activePlayer == playerOne) {
            activePlayer = playerTwo;
        } else
            activePlayer = playerOne;
    }

    private static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    private static void displayBoard(GameField activePlayer) {
        activePlayer.displayFogBoard();
        System.out.println("---------------------");
        activePlayer.displayGameField();
    }

    private static void setPlayerOne(PlayerOneField player) {
        String playerName = "Player 1";
        System.out.printf("\n%s, place your ships on the game field\n\n", playerName);
        player.displayGameField();

        while (shipCounter < 5) {
            System.out.printf(ADD_SHIP, ship[shipCounter].getName(), ship[shipCounter].getSize());
            getCoordinateInput();
        }
        promptEnterKey();

    }
    private static void setPlayerTwo(PlayerTwoField player) {
        String playerName = "Player 2";
        System.out.printf("\n%s, place your ships on the game field\n\n", playerName);
        player.displayGameField();

        while (shipCounter < 5) {
            System.out.printf(ADD_SHIP, ship[shipCounter].getName(), ship[shipCounter].getSize());
            getCoordinateInput();
        }
    }

    private static void getCoordinateInput() {
        try {
            String[] inputCords = scanner.nextLine().split(" ");
            if (!Pattern.matches("^[A-J]+(?:[1-9]|0[1-9]|10)$", inputCords[0]) ||
                    !Pattern.matches("^[A-J]+(?:[1-9]|0[1-9]|10)$", inputCords[1])) {
                System.out.print(ERROR);
                getCoordinateInput();
            }
            convertCoordinates(inputCords);
        } catch (Exception ignored) {
        }

    }

    private static void convertCoordinates(String[] inputCords) {
        int[] c1 = {inputCords[0].charAt(0) - 'A', Integer.parseInt(inputCords[0].substring(1)) - 1};
        int[] c2 = {inputCords[1].charAt(0) - 'A', Integer.parseInt(inputCords[1].substring(1)) - 1};

        int[] coordinateOne = {Math.min(c1[0], c2[0]), Math.min(c1[1], c2[1])};
        int[] coordinateTwo = {Math.max(c1[0], c2[0]), Math.max(c1[1], c2[1])};
        processCoordinates(coordinateOne, coordinateTwo);

    }

    private static void processCoordinates(int[] coordinateOne, int[] coordinateTwo) {
        try {
            if (((coordinateTwo[0] - coordinateOne[0] + 1) != ship[shipCounter].getSize()
                    && (coordinateTwo[1] - coordinateOne[1] + 1) != ship[shipCounter].getSize())
                    || (coordinateOne[0] != coordinateTwo[0] && coordinateOne[1] != coordinateTwo[1])) {
                System.out.printf(ERROR_1, ship[shipCounter].getName());
                getCoordinateInput();
                return;
            }

            if (activePlayer.checkGameField(coordinateOne, coordinateTwo)) {
                System.out.println("Game field clear");
                activePlayer.placeShip(coordinateOne, coordinateTwo);
                if (activePlayer == playerOne) {
                    ship[shipCounter].setC1(coordinateOne);
                    ship[shipCounter].setC2(coordinateTwo);
                } else {
                    ship[shipCounter].setC3(coordinateOne);
                    ship[shipCounter].setC4(coordinateTwo);
                }
                activePlayer.displayGameField();
                shipCounter++;
            } else {
                System.out.print(ERROR_2);
            }
        } catch (Exception ignored) {
        }
    }

    private static void startGame() {
        System.out.println("\nThe game starts!\n");
        String player = "Player 1";
        while (!playerOne.allShipSank() || !playerTwo.allShipSank()) {
            promptEnterKey();
            resetActivePlayer();
            displayBoard(activePlayer);
            System.out.printf("\n%s, it's your turn:\n", player);
            if (player.equals("Player 1")) {
                player = "Player 2";
            } else
                player = "Player 1";
            getShot();
        }

    }

    private static void getShot() {
        System.out.print("Make Shot:- ");
        String shoot = scanner.nextLine();
        shoot(shoot);
    }

    private static void shoot(String shoot) {
        if (!Pattern.matches("^[A-J]+(?:[1-9]|0[1-9]|10)$", shoot)) {
            System.out.print(ERROR_4);
            getShot();
        }
        switch (activePlayer.isShipHit(shoot)) {
            case 0:
            case 3:
                System.out.println("\nYou missed!..");
                break;
            case 1:
                if (playerOne.allShipSank() || playerTwo.allShipSank()) {
                    displayBoard(activePlayer);
                    System.out.println("You sank the last ship. You won. Congratulations!");
                    System.exit(0);
                } else if(playerOne.ship1_Sank() && p1_ship1_down || playerTwo.ship1_Sank() && p2_ship1_down) {
                    if (playerOne.ship1_Sank() && p1_ship1_down) {
                        p1_ship1_down = false;
                    } else if (playerTwo.ship1_Sank() && p2_ship1_down) {
                        p2_ship1_down = false;
                    }
                    System.out.println("You sank a ship!..");
                    break;
                } else if (playerOne.ship2_Sank() && p1_ship2_down || playerTwo.ship2_Sank() && p2_ship2_down) {
                    if (playerOne.ship2_Sank() && p1_ship2_down) {
                        p1_ship2_down = false;
                    } else if (playerTwo.ship2_Sank() && p2_ship2_down) {
                        p2_ship2_down = false;
                    }
                    System.out.println("You sank a ship!..");
                    break;
                } else if (playerOne.ship3_Sank() && p1_ship3_down || playerTwo.ship3_Sank() && p2_ship3_down) {
                    if (playerOne.ship3_Sank() && p1_ship3_down) {
                        p1_ship3_down = false;
                    } else if (playerTwo.ship3_Sank() && p2_ship3_down) {
                        p2_ship3_down = false;
                    }
                    System.out.println("You sank a ship!..");
                    break;
                } else if (playerOne.ship4_Sank() && p1_ship4_down || playerTwo.ship4_Sank() && p2_ship4_down) {
                    if (playerOne.ship4_Sank() && p1_ship4_down) {
                        p1_ship4_down = false;
                    } else if (playerTwo.ship4_Sank() && p2_ship4_down) {
                        p2_ship4_down = false;
                    }
                    System.out.println("You sank a ship!..");
                    break;
                } else if (playerOne.ship5_Sank() && p1_ship5_down || playerTwo.ship5_Sank() && p2_ship5_down) {
                    if (playerOne.ship5_Sank() && p1_ship5_down) {
                        p1_ship5_down = false;
                    } else if (playerTwo.ship5_Sank() && p2_ship5_down) {
                        p2_ship5_down = false;
                    }
                    System.out.println("You sank a ship!..");
                    break;
                }
                System.out.println("\nYou hit a ship!");
                break;
            case 2:
                System.out.println("\nShip has already been hit at this spot!..");
                break;
            default:
                System.out.println("Invalid input..Try again!");
                getShot();
                break;
        }

    }

}
