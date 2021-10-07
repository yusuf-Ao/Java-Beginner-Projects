
import java.util.Arrays;
import java.util.Scanner;

public class CinemaRoom {

    static int currentIncome = 0;
    static int totalIncome = 0;
    static int ticketSold = 0;
    static char[][] room = new char[10][10];
    static final Scanner scanner = new Scanner(System.in);

    protected CinemaRoom() {
        for (char[] seat : room) {
            Arrays.fill(seat, 'S');
        }
    }

    protected static void buyTicket(int roomRow, int roomSeats) {
        int price = 0;
        System.out.println("\nEnter a row number: ");
        int rowChoice = scanner.nextInt();
        System.out.println("Enter a seat number in that row: ");
        int seatChoice = scanner.nextInt();
        try {
            if (rowChoice > roomRow || seatChoice > roomSeats) {
                System.out.println("Wrong input!");
                buyTicket(roomRow, roomSeats);
            } else {
                int seats = roomRow * roomSeats;
                int frontHalf;

                if ('B' == room[rowChoice][seatChoice]) {
                    System.out.println("That ticket has already been purchased!");
                    buyTicket(roomRow, roomSeats);
                } else{
                    room[rowChoice][seatChoice] = 'B';
                    if (seats <= 60) {
                        price += 10;
                        currentIncome +=10;
                    } else {
                        frontHalf = Math.floorDiv(roomRow, 2);
                        if (rowChoice <= frontHalf) {
                            price += 10;
                            currentIncome +=10;
                        } else {
                            price += 8;
                            currentIncome +=8;
                        }
                    }
                    ticketSold++;
                    System.out.printf("Ticket price: $%d\n", price);
                }
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }

    }

    protected static void showSeats(int roomRow, int roomSeats) {
        int rowNo = 1;
        System.out.println("Cinema:");
        //-----------------------------------
        System.out.print(" ");
        for (int i = 1; i <= roomSeats; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        //-----------------------------------
        for (int i = 1; i <= roomRow; i++) {
            System.out.print(rowNo);
            for (int j = 1; j <= roomSeats; j++) {
                System.out.print(" " + room[i][j]);
            }
            rowNo++;
            System.out.println();
        }
    }

    protected static void statistics(int roomRow, int roomSeats) {
        int seats = roomRow * roomSeats;
        double percentage =  (ticketSold / (double)seats) * 100;
        totalIncome(roomRow, roomSeats);
        System.out.printf("\nNumber of purchased tickets: %d", ticketSold);
        System.out.printf("\nPercentage: %.2f%%", percentage);
        System.out.printf("\nCurrent income: $%d", currentIncome);
        System.out.printf("\nTotal income: $%d\n", totalIncome);
    }

    protected static void totalIncome(int roomRow, int roomSeats) {
        int seats = roomRow * roomSeats;
        if (seats <= 60) {
            totalIncome = seats * 10;
        } else {
            int frontHalf = (roomRow / 2);
            int backHalf = (roomRow - frontHalf);
            totalIncome = ((frontHalf * 10) + (backHalf * 8)) * roomSeats;
        }
    }

}
