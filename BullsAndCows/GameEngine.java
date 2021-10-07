import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

public class GameEngine {
    private Scanner scanner;
    private final Random random = new Random();
    public final int MAX_LENGTH = 36;
    private String secretCode;
    private int numberOfTurns;
    private boolean victory;
    private char[] chars;
    private int lengthSecretCode;
    private int numberOfPossibleSymbols;

    public boolean isVictory() {
        return victory;
    }

    public GameEngine() {
        generateSymbols();
        setInputStream(System.in);
    }

    public void setInputStream(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public void Start() {
        victory = false;
        generateSecretCode();
        System.out.println("Okay, let's start a game!");
    }

    public void Stop() {
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private void generateSymbols() {
        chars = new char[MAX_LENGTH];
        for (int i = 0; i < 10; i++) {
            chars[i] = (char) ('0' + i);
        }
        int j = 10;
        for (int i = 0; i < 26; i++) {
            chars[j] = (char) ('a' + i);
            j++;
        }
    }

    private void shuffleSymbols() {
        for (int i = numberOfPossibleSymbols - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char a = chars[index];
            chars[index] = chars[i];
            chars[i] = a;
        }
    }

    private void generateSecretCode() {
        System.out.println("Input the length of the secret code:");
        String length = scanner.nextLine().trim();
        try {
            lengthSecretCode = Integer.parseInt(length);
        } catch (NumberFormatException e) {
            System.out.printf("Error: %s isn't a valid number.", lengthSecretCode);
            System.exit(0);
        }

        System.out.println("Input the number of possible symbols in the code");
        String symbols = scanner.nextLine().trim();
        try {
            numberOfPossibleSymbols = Integer.parseInt(symbols);
        } catch (NumberFormatException e) {
            System.out.printf("Error: %s isn't a valid number.", numberOfPossibleSymbols);
            System.exit(0);
        }

        if (lengthSecretCode > MAX_LENGTH) {
            System.out.printf("Error: can't generate a secret number with a length of %d because" +
                    " there aren't enough unique digits.", lengthSecretCode);
            System.exit(0);
        }else if (numberOfPossibleSymbols > MAX_LENGTH) {
            System.out.printf("Error: can't generate a secret number with a length of %d because" +
                    " there aren't enough unique digits.", numberOfPossibleSymbols);
            System.exit(0);
        }else if (numberOfPossibleSymbols < lengthSecretCode) {
            System.out.printf("Error: it's not possible to generate a code " +
                    "with a length of %d with %d unique symbols.", lengthSecretCode, numberOfPossibleSymbols);
            System.exit(0);
        }else if (lengthSecretCode <= 0) {
            System.out.printf("Error: it's not possible to generate a code " +
                    "with a length of %d with %d unique symbols.", lengthSecretCode, numberOfPossibleSymbols);
            System.exit(0);
        }else {
            shuffleSymbols();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < lengthSecretCode; i++) {
                stringBuilder.append(chars[i]);
            }
            secretCode = stringBuilder.toString();
            printMessageTheSecretIsPrepared();
        }
    }

    private void printMessageTheSecretIsPrepared() {
        generateSymbols();
        StringBuilder builder = new StringBuilder();
        builder.append("The secret is prepared: ");
        builder.append("*".repeat(lengthSecretCode));
        builder.append(" ");

        int numbers = 0;
        int letters = 0;
        for (int i = 0; i < numberOfPossibleSymbols; i++) {
            if (Character.isLetter(chars[i])) {
                letters++;
            } else {
                numbers++;
            }
        }
        builder.append("(");
        builder.append(chars[0]);
        if (numbers > 1) {
            builder.append("-");
            builder.append(chars[numbers - 1]);
        }
        if (letters > 0) {
            builder.append(", ");
            builder.append(chars[10]);
            if (letters > 1) {
                builder.append("-");
                builder.append(chars[numbers + letters - 1]);
            }
        }
        builder.append(").");
        System.out.println(builder.toString());
    }

    public void nextTurn() {
        numberOfTurns++;
        System.out.printf("Turn %d:\n", numberOfTurns);
        String input = scanner.next();
        if (input.length() != lengthSecretCode) {
            System.out.printf("Error: the length of your input should match " +
                    "that of the secret code(%d)", lengthSecretCode);
            System.exit(0);
        }
        gradeATurn(input);
        if (secretCode.equals(input)) {
            victory = true;
        }
    }

    private void gradeATurn(String input) {
        int cows = 0;
        int bulls = 0;
        for (int j = 0; j < input.length(); j++) {
            if (secretCode.contains(String.valueOf(input.charAt(j)))) {
                if (secretCode.charAt(j) == input.charAt(j)) {
                    bulls++;
                } else {
                    cows++;
                }
            }
        }
        printGrade(bulls, cows);
    }

    private void printGrade(int bulls, int cows) {
        if (cows > 0 & bulls > 0) {
            System.out.printf("Grade: %d %s and %d %s\n",
                    bulls, bulls > 1 ? "bulls" : "bull", cows, cows > 1 ? "cows" : "cow");
        } else if (bulls > 0) {
            System.out.printf("Grade: %d %s\n", bulls, bulls > 1 ? "bulls" : "bull");
        } else if (cows > 0) {
            System.out.printf("Grade: %d %s\n", cows, cows > 1 ? "cows" : "cow");
        } else {
            System.out.println("Grade: None");
        }
    }
}
