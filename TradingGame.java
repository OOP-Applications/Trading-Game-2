import java.text.*;
import java.util.*;

class TradingGame{
    public static void main(String[] args){

    }

    public static void printMenu(){

    }

    public static int getChoice(){
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Your choice: ");
            choice = keyboard.nextInt();
        } while (choice > 7 || choice < 1);
        return choice;
    }

    public static String currency Formatter(double amount){

    }

    public static double computePrice(double basePrice, double variation){

    }

    public static int getQuantity(String product, String action){

    }

    public static boolean sellApples(int amount){

    }

    public static boolean sellPears(int amount){

    }

    public static boolean buyApples(int amount){

    }

    public static boolean buyPears(int amount){

    }
}
