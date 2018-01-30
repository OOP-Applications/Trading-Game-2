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
      return ((int)((basePrice + (Math.random() > .5 ? 1 : -1) * variation) * 100))/100.0;
    }

    public static int getQuantity(String product, String action){
      System.out.print("How many " + product + " do you want to " + action + "? ");
      Scanner keyboard = new Scanner(System.in);
      return keyboard.nextInt();
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
