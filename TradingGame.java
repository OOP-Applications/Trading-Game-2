import java.text.*;
import java.util.*;

class TradingGame{

    static final int NUMBER_OF_DAYS = 10;
    static final double BASE_PRICE = 10;
    static final double VARIATION = 5;
    static final double INITIAL_CASH = 100;

    static double cash = INITIAL_CASH;
    static int appleInventory = 0;
    static int pearInventory = 0;
    static double applePrice, pearPrice, NYapplePrice, NYpearPrice, LAapplePrice, LApearPrice;

    static int NYappleinventory = (int) (20 + (Math.random() * 10));
    static int NYpearinventory = (int) (20 + (Math.random() * 10));
    static int LAappleinventory = (int) (20 + (Math.random() * 10));
    static int LApearinventory = (int) (20 + (Math.random() * 10));




    public static void main(String[] args){
        for (int day = 1; day <= NUMBER_OF_DAYS; day++){
            NYapplePrice = computePrice(BASE_PRICE, VARIATION);
            NYpearPrice = computePrice(BASE_PRICE, VARIATION);
            LAapplePrice = computePrice(BASE_PRICE, VARIATION);
            LApearPrice = computePrice(BASE_PRICE, VARIATION);
            System.out.println("Day: " + day + " out of 10");
            int choice;
            int amount;
            Scanner keyboard = new Scanner(System.in);
            System.out.println("The prices for New York are "+NYapplePrice+" for apples and "+NYpearPrice+" for pears.");
            System.out.println("The prices in Los Angeles are "+LAapplePrice+" for apples and "+LApearPrice+" for pears.");
            System.out.println("Would you like to trade in New York or Los Angeles? Enter 1 for New York or 2 for Los Angles");
            int location = keyboard.nextInt();

            if (location == 1){
                applePrice = NYapplePrice;
                pearPrice = NYpearPrice;
            }
            else{
                applePrice = LAapplePrice;
                pearPrice = LApearPrice;
            }
            do{
                printMenu();
                choice = getChoice();
                switch (choice){
                    case 1: // Print cash balance and inventory
                        System.out.println("Cash: " + currencyFormatter(cash));
                        System.out.println("Apple inventory: " + appleInventory);
                        System.out.println("Pear inventory: " + pearInventory);

                        break;
                    case 2: //Print today's prices
                        System.out.println("The price of apples is: " +
                        currencyFormatter(applePrice));
                        System.out.println("The price of pears is: " +
                        currencyFormatter(pearPrice));
                        break;
                    case 3: //Buy apples
                        amount = getQuantity("apples", "buy");
                        if (!buyApples(amount)) {
                            System.out.println("You don't have enough money.");
                        }
                        break;
                    case 4: // Sell apples
                        amount = getQuantity("apples", "sell");
                        if (!sellApples(amount)){
                            System.out.println("You don't have enough apples.");
                        }
                        break;
                    case 5: { // Buy buyPears
                        amount = getQuantity("pears", "buy");
                        if (!buyPears(amount)){
                          System.out.println("You dont have enough money");
                          }
                        break;
                        }
                    case 6: { // Sell Pears
                        amount = getQuantity("pears", "sell");
                        if (!sellPears(amount)){
                          System.out.println("You dont have enough pears");
                          }
                        break;
                    }
                }
            }   while (choice != 7);
        }
        System.out.println("You finished with: " + currencyFormatter(cash));

    }

    public static void printMenu(){
      System.out.println("1. Print cash balance and inventory");
      System.out.println("2. Print today's prices");
      System.out.println("3. Buy apples");
      System.out.println("4. Sell Apples");
      System.out.println("5. Buy pears");
      System.out.println("6. Sell pears");
      System.out.println("7. I am done for today");

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

    public static String currencyFormatter(double amount){
      DecimalFormat myFormatter = new DecimalFormat("$###,###.00");
      return myFormatter.format(amount);
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
