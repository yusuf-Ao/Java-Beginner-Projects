import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        char[][] array = new char[3][3];

        //populating the array with spaces
        for (char[] value : array) {
            Arrays.fill(value, ' ');
        }

        System.out.println("---------");
        for (char[] chars : array) {
            System.out.print("| ");
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        boolean incorrectInput = true;
        char player = 'O';

        while (checkWinner(array) == null) {
            checkWinner(array);
            do {
                System.out.println("Enter the coordinates: ");
                if (!scanner.hasNext()) {
                    scanner.nextLine();
                } else {
                    try {
                        String input = scanner.nextLine().replaceAll("\\s", "");
                        int coordinate = Integer.parseInt(input);
                        String  newCoordinate = String.valueOf(coordinate);
                        int x = newCoordinate.charAt(0);
                        int y = newCoordinate.charAt(1);
                        if (x > '3' || x < '1' || y > '3' || y < '1') {
                            System.out.println("Coordinates should be from 1 to 3!");
                            incorrectInput = true;
                        } else if (array[x - '1'][y - '1'] != ' '){
                            System.out.println("This cell is occupied! Choose another one!");
                            incorrectInput = true;
                        } else {
                            if (player == 'X') {
                                player = 'O';
                            } else {
                                player = 'X';
                            }
                            array[x - '1'][y - '1'] = player;
                            System.out.println("---------");
                            for (char[] chars : array) {
                                System.out.print("| ");
                                for (char aChar : chars) {
                                    System.out.print(aChar + " ");
                                }
                                System.out.println("|");
                            }
                            System.out.println("---------");
                            incorrectInput = false;
                        }

                    }catch(NumberFormatException e) {
                        System.out.println("You should enter numbers!");
                        incorrectInput = true;
                    }
                }
            } while (incorrectInput);
        }

    }
    private  static String checkWinner(char[][] array) {
        String winner = null;
        if (isO_Winner(array)) {
            winner = "O wins";
            System.out.println("O wins");
        } else if (isX_Winner(array)) {
            winner = "X wins";
            System.out.println("X wins");
        } else if (!isX_Winner(array) && !isO_Winner(array) && !isIncomplete(array)){
            winner = "Draw";
            System.out.println("Draw");
        }

        return winner;
    }

    private static boolean isX_Winner(char[][] array) {
        boolean result = false;
        char sumPr = 0;
        char sumSec = 0;
        for (char[] chars : array) {
            for (int j = 1; j < chars.length - 1; j++) {
                //checking the horizontal elements
                if (chars[j - 1] + chars[j] + chars[j + 1] == 264) {
                    result = true;
                    break;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                //checking the principal diagonal elements
                if (i == j) {
                    sumPr += array[i][j];
                    if (sumPr == 264) {
                        result = true;
                        break;
                    }
                }
                //checking the secondary diagonal elements
                if (i + j == array.length - 1) {
                    sumSec += array[i][j];
                    if (sumSec == 264) {
                        result = true;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array[i].length - 1; j++) {
                //checking the vertical elements
                if (array[j - 1][i] + array[j][i] + array[j + 1][i] == 264) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private static boolean isO_Winner(char[][] array) {
        boolean result = false;
        char sumPr = 0;
        char sumSec = 0;
        for (char[] chars : array) {
            for (int j = 1; j < chars.length - 1; j++) {
                //checking the horizontal elements
                if (chars[j - 1] + chars[j] + chars[j + 1] == 237) {
                    result = true;
                    break;
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                //checking the principal diagonal elements
                if (i == j) {
                    sumPr += array[i][j];
                    if (sumPr == 237) {
                        result = true;
                        break;
                    }
                }
                //checking the secondary diagonal elements
                if (i + j == array.length - 1) {
                    sumSec += array[i][j];
                    if (sumSec == 237) {
                        result = true;
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array[i].length - 1; j++) {
                //checking the vertical elements
                if (array[j - 1][i] + array[j][i] + array[j + 1][i] == 237) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private static boolean isIncomplete(char[][] array) {
        boolean result = false;
        for (char[] chars : array) {
            for (int j = 0; j < array.length; j++) {
                if (chars[j] == ' ') {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

}

