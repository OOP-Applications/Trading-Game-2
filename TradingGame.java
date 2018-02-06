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
    static double applePrice, pearPrice;
    static final ArrayList<String> fruitlist = new ArrayList<String> ();
    static final Hashtable<String, Double> prices = new Hashtable<String, Double> ();
    static final Hashtable<String, Integer> inventories = new Hashtable<String, Integer> ();


    public static void main(String[] args){
        for (int day = 1; day <= NUMBER_OF_DAYS; day++){
            applePrice = computePrice(BASE_PRICE, VARIATION);
            pearPrice = computePrice(BASE_PRICE, VARIATION);
            prices.put("apple", applePrice);
            prices.put("pear", pearPrice);
            inventories.put("apple", appleInventory);
            inventories.put("pear", pearInventory);
            fruitlist.asList("apple", "pear");
            System.out.println("Day: " + day + " out of 10");
            int choice;
            int amount;
            do{
                printMenu();
                choice = getChoice();
                switch (choice){
                    case 1: // Print cash balance and inventory
                        System.out.println("Cash: " + currencyFormatter(cash));
                        System.out.println("Apple inventory: " + inventories.get("apple"));
                        System.out.println("Pear inventory: " + inventories.get("pear"));

                        break;
                    case 2: //Print today's prices
                        System.out.println("The price of apples is: " +
                        currencyFormatter(applePrice));
                        System.out.println("The price of pears is: " +
                        currencyFormatter(pearPrice));
                        break;
                    case 3: //Buy Fruit
                        amount = getQuantity("apples", "buy");
                        if (!buyFruits(amount, "apples")) {
                            System.out.println("You don't have enough money.");
                        }
                        break;
                    case 4: // Sell Fruit
                        System.out.println("What fruit do you want to sell?");
                        System.out.println("These are the available fruits " + fruitlist);
                        Scanner keyboard2 = new Scanner(System.in);
                        String fruit = keyboard2.nextLine();
                        amount = getQuantity(fruit, "sell");
                        if (!sellFruits(amount, fruit)){
                            System.out.println("You don't have enough " + fruit);
                        }
                        break;
                    }
                }
            }   while (choice != 5);
        }
        System.out.println("You finished with: " + currencyFormatter(cash));

    }

    public static void printMenu(){
      System.out.println("1. Print cash balance and inventory");
      System.out.println("2. Print today's prices");
      System.out.println("3. Buy Fruit");
      System.out.println("4. Sell Apples");
      System.out.println("5. I am done for today");

    }

    public static int getChoice(){
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Your choice: ");
            choice = keyboard.nextInt();
        } while (choice > 5 || choice < 1);
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
    public static boolean sellFruits(int amount, String fruit){
        double price = prices.get(fruit);
        int inventory = inventories.get(fruit);
        if (amount > inventory) {
            return false;
        }
        cash += amount * price;
        inventory -= amount;
        inventories.put(fruit, inventory);
        return true;
    }
    public static boolean buyFruits(int amount, String fruit){
        double price = prices.get(fruit);
        int inventory = inventories.get(fruit);
        if (amount * price < cash) {
            cash -= amount * price;

            inventory += amount;
            inventories.put(fruit, inventory);
            return true;
        }
        return false;
    }
}
