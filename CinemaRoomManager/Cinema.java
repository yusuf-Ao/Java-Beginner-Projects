
import java.util.Scanner;

public class Cinema {
    static final Scanner scanner = new Scanner(System.in);
    static int menu;
    static CinemaRoom cinemaRoom = new CinemaRoom();
    public static void main(String[] args) {

        System.out.println("Enter the number of rows: ");
        int roomRow = scanner.nextInt();

        System.out.println("Enter the number of seats in each row: ");
        int roomSeats = scanner.nextInt();

        menuOption(roomRow, roomSeats);
    }

    private static void menuOption(int roomRow, int roomSeats) {
        System.out.println("\n1. Show the seats" +
                "\n2. Buy a ticket" +
                "\n3. Statistics" +
                "\n0. Exit");
        System.out.println("\nSelect option: ");
        menu = scanner.nextInt();
        switch (menu) {
            case 0:
                break;
            case 1:
                CinemaRoom.showSeats(roomRow, roomSeats);
                menuOption(roomRow,roomSeats);
                break;
            case 2:
                CinemaRoom.buyTicket(roomRow, roomSeats);
                menuOption(roomRow,roomSeats);
                break;
            case 3:
                CinemaRoom.statistics(roomRow, roomSeats);
                menuOption(roomRow,roomSeats);
                break;
            default:
                System.out.println("Incorrect option..Try again!");
                menuOption(roomRow,roomSeats);
                break;
        }
    }

}
