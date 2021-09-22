import java.util.Locale;
import java.util.Scanner;

public class CoffeeMachine {
    final static Scanner sc = new Scanner(System.in);
    static int[] ingredients = {550, 400, 540, 120, 9};
    static int cash = ingredients[0];
    static int water = ingredients[1];
    static int milk = ingredients[2];
    static int cBeans = ingredients[3];
    static int disCups = ingredients[4];
    static boolean exit = false;

    public static void main(String[] args) {

        action();
    }

    private static void action() {

        do {
            System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
            if (!sc.hasNext()) {
                sc.nextLine();
            } else {
                String action = sc.nextLine().toLowerCase(Locale.ROOT);
                switch (action) {
                    case "buy":
                        buy();
                        break;
                    case "fill":
                        fill();
                        break;
                    case "take":
                        take();
                        break;
                    case "remaining":
                        machineStatus();
                        break;
                    case "exit":
                        exit = true;
                        break;
                    default:
                        break;
                }
            }

        } while (!exit);

    }

    private static void machineStatus() {
        System.out.println("\nThe coffee machine has: ");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(cBeans + " g of coffee beans");
        System.out.println(disCups + " disposable cups");
        System.out.printf("$%d of money\n", cash);
    }

    private static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        var input = sc.next();

        switch (input) {
            case "1":
                if (water >= 250 && cBeans >= 16 && disCups > 0) {
                    water -= 250;
                    cBeans -= 16;
                    disCups -= 1;
                    cash += 4;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if(water < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if(cBeans < 16) {
                    System.out.println("Sorry not enough coffee beans");
                } else if(disCups <= 0) {
                    System.out.println("Hey i'm out of cups!!..");
                }
                break;
            case "2":
                if (water >= 350 && milk >= 75 && cBeans >= 20 && disCups > 0) {
                    water -= 350;
                    milk -= 75;
                    cBeans -= 20;
                    disCups -= 1;
                    cash += 7;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if(milk < 75) {
                    System.out.println("Sorry not enough milk");
                } else if(cBeans < 20) {
                    System.out.println("Sorry not enough coffee beans");
                } else if(disCups <= 0) {
                    System.out.println("Hey i'm out of cups!!..");
                }
                break;
            case "3":
                if (water >= 200 && milk >= 100 && cBeans >= 12 && disCups > 0) {
                    water -= 200;
                    milk -= 100;
                    cBeans -= 12;
                    disCups -= 1;
                    cash += 6;
                    System.out.println("I have enough resources, making you a coffee!");
                } else if (water < 200) {
                    System.out.println("Sorry, not enough water!");
                } else if(milk < 100) {
                    System.out.println("Sorry not enough milk");
                } else if(cBeans < 12) {
                    System.out.println("Sorry not enough coffee beans");
                } else if(disCups <= 0) {
                    System.out.println("Hey i'm out of cups!!..");
                }
                break;
            case "back":
                main(null);
            default:
                break;
        }
    }

    private static void fill() {
        System.out.println("Write how many ml of water you want to add: ");
        int addWater = sc.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        int addMilk = sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int addCoffeeBeans = sc.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int addDisCups = sc.nextInt();
        water += addWater;
        milk += addMilk;
        cBeans += addCoffeeBeans;
        disCups += addDisCups;
    }

    private static void take() {
        System.out.println("I gave you $" + cash);
        cash -= cash;
    }
}
