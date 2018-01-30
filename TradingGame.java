import java.text.*;
import java.util.*;

class TradingGame{
    public static void main(String[] args){

    }

    public static void printMenu(){

    }

    public static int getChoice(){

    }

    public static String currency Formatter(double amount){

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
