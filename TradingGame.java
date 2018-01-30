import java.text.*;
import java.util.*;

class TradingGame{
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

    }

    public static boolean sellPears(int amount){

    }

    public static boolean buyApples(int amount){

    }

    public static boolean buyPears(int amount){

    }
}
