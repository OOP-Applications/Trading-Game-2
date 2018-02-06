   import java.text.*;
import java.util.*;

class TradingGame{

    static final int NUMBER_OF_DAYS = 10;
    static final double BASE_PRICE = 10;
    static final double VARIATION = 5;
    static final double INITIAL_CASH = 100;

    static double cash = INITIAL_CASH;
    static double appleInventory = 0;
    static double pearInventory = 0;
    static double applePrice, pearPrice;

    static final Hashtable<String, Double> prices = new Hashtable<String, Double> ();
    static final Hashtable<String, Double> inventories = new Hashtable<String, Double> ();


    public static void main(String[] args){
        for (int day = 1; day <= NUMBER_OF_DAYS; day++){
            applePrice = computePrice(BASE_PRICE, VARIATION);
            pearPrice = computePrice(BASE_PRICE, VARIATION);
            prices.put("apples", applePrice);
            prices.put("pears", pearPrice);
            inventories.put("apples", appleInventory);
            inventories.put("pears", pearInventory);
            System.out.println("Day: " + day + " out of 10");
            int choice;
            int amount;
            do{
                printMenu();
                choice = getChoice();
                switch (choice){
                    case 1: // Print cash balance and inventory
                        System.out.println("Cash: " + currencyFormatter(cash));
                        System.out.println("Apple inventory: " + inventories.get("apples"));
                        System.out.println("Pear inventory: " + inventories.get("pears"));

                        break;
                    case 2: //Print today's prices
                        System.out.println("The price of apples is: " +
                        currencyFormatter(applePrice));
                        System.out.println("The price of pears is: " +
                        currencyFormatter(pearPrice));
                        break;
                    case 3: //Buy apples
                        amount = getQuantity("apples", "buy");
                        if (buyFruits(amount, "apples")) {
                            System.out.println("You don't have enough money.");
                        }
                        break;
                    case 4: // Sell apples
                        amount = getQuantity("apples", "sell");
                        if (!sellFruits(amount, "apples")){
                            System.out.println("You don't have enough apples.");
                        }
                        break;
                    case 5: { // Buy Pears
                        amount = getQuantity("pears", "buy");
                        if (!buyFruits(amount, "pears")){
                          System.out.println("You dont have enough money");
                          }
                        break;
                        }
                    case 6: { // Sell Pears
                        amount = getQuantity("pears", "sell");
                        if (!sellFruits(amount, "pears")){
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
    public static boolean sellFruits(double amount, String fruit){
        double price = prices.get(fruit);
        double inventory = inventories.get(fruit);
        if (amount > inventory) {
            return false;
        }
        cash += amount * price;
        inventory -= amount;
        inventories.put(fruit, inventory);
        return true;
    }
    public static boolean buyFruits(double amount, String fruit){
        double price = prices.get(fruit);
        double inventory = inventories.get(fruit);
        if (amount * price < cash) {
            cash -= amount * price;

            inventory += amount;
            inventories.put(fruit, inventory);
            return true;
        }
        return false;
    }
}
