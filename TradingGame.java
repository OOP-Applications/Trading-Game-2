import java.text.*;
import java.util.*;

class TradingGame{
    static final int NUMBER_OF_DAYS=10;
    static final double BASE_PRICE=10;
    static final double VARIATION = 5;
    static final double INIITAL_CASH=100;

    static double cash=INIITAL_CASH;
    static int appleinventory=0;
    static int pearinventory=0;
    static double applePrice, pearPrice
    public static void main(String[] args){

    }

    public static void printMenu(){
      System.out.println("1. Print cash balance and inventory");
      System.out.println("2. Print today's prices");
      System.out.println("3. Buy apples");
      System.out.println("4. Sell Apples");
      System.out.println("5. Buy pears");
      System.out.println("6. Sell pears");
      Sysetm.out.println("7. I am done for today");

    }

    public static int getChoice(){

    }

    public static String currency Formatter(double amount){
      DecimalFormat myFormatter = new DecimalFormat("$###,###.00");
      return myFormatter.format(amount);
    }

    public static double computePrice(double basePrice, double variation){

    }

    public static int getQuantity(String product, String action){

    }

    public static boolean sellApples(int amount){
        if (amount > appleInventory) {
            return false;
        }
        cash += amount * applePrice;
        appleInventory -= amount;
        return true;
    }

    public static boolean sellPears(int amount){
        if (amount > pearInventory) {
            return false;
        }
        cash += amount * pearPrice;
        pearInventory -= amount;
        return true;
    }

    public static boolean buyApples(int amount){
        if (amount * applePrice < cash) {
            cash -= amount * applePrice;
            appleInventory += amount;
            return true;
        }
        return false;
    }

    public static boolean buyPears(int amount){

        if (amount * pearPrice < cash) {
            cash -= amount * pearPrice;
            pearInventory += amount;
            return true;
        }
        return false;



    }
}
