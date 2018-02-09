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

    static final Hashtable<String, Double> prices = new Hashtable<String, Double> ();
    static final Hashtable<String, Integer> inventories = new Hashtable<String, Integer> ();


    public static void main(String[] args){
        List<String> fruitlist = new ArrayList<>();
        fruitlist.add("pear");
        fruitlist.add("apple");
        inventories.put("apple", appleInventory);
        inventories.put("pear", pearInventory);
        for (int day = 1; day <= NUMBER_OF_DAYS; day++){
            int listlen = fruitlist.size();
            for (int i = 0; i < listlen; i++){
              String fruiter = fruitlist.get(i);
              prices.put(fruiter, computePrice(BASE_PRICE, VARIATION));
              inventories.put(fruiter, inventories.get(fruiter));
              i += 1;
            }
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
                      for (int day = 1; day <= NUMBER_OF_DAYS; day++){
                        System.out.println("The price of apples is: " +
                        currencyFormatter(prices.get("apple")));
                      }
                        System.out.println("The price of apples is: " +
                        currencyFormatter(prices.get("apple")));
                        System.out.println("The price of pears is: " +
                        currencyFormatter(prices.get("pear")));
                        break;
                    case 3: //Buy Fruit
                        System.out.println("What fruit do you want to buy?");
                        System.out.println("These are the available fruits " + fruitlist);
                        Scanner buykeyboard = new Scanner(System.in);
                        String buyfruit = buykeyboard.nextLine();
                        amount = getQuantity(buyfruit, "buy");
                        if (!buyFruits(amount, buyfruit)) {
                            System.out.println("You don't have enough money.");
                        }
                        break;
                    case 4: // Sell Fruit
                        System.out.println("What fruit do you want to sell?");
                        System.out.println("These are the available fruits " + fruitlist);
                        Scanner sellkeyboard = new Scanner(System.in);
                        String sellfruit = sellkeyboard.nextLine();
                        amount = getQuantity(sellfruit, "sell");
                        if (!sellFruits(amount, sellfruit)){
                            System.out.println("You don't have enough " + sellfruit);
                        }
                        break;
                    case 5:
                        System.out.println("What fruit do you want to add?");
                        System.out.println("These fruits are already being sold " + fruitlist);
                        Scanner addkeyboard = new Scanner(System.in);
                        String newfruit = addkeyboard.nextLine();
                        fruitlist.add(newfruit);


                }
            }
            while (choice != 6);
        }


        System.out.println("You finished with: " + currencyFormatter(cash));
    }



    public static void printMenu(){
      System.out.println("1. Print cash balance and inventory");
      System.out.println("2. Print today's prices");
      System.out.println("3. Buy Fruit");
      System.out.println("4. Sell Fruit");
      System.out.println("5. Add Fruit");
      System.out.println("6. I am done for today");
    }

    public static int getChoice(){
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Your choice: ");
            choice = keyboard.nextInt();
        } while (choice > 6 || choice < 1);
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
