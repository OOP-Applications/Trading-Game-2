import java.text.*;
import java.util.*;

class TradingGame{

    static final int NUMBER_OF_DAYS = 10;
    static final double BASE_PRICE = 10;
    static final double VARIATION = 5;
    static final double INITIAL_CASH = 100;

    static double cash = INITIAL_CASH;

    //This creates the hashtables to be used to keep track of prices and inventories.
    static final Hashtable<String, Double> prices = new Hashtable<String, Double> ();
    static final Hashtable<String, Integer> inventories = new Hashtable<String, Integer> ();


    public static void main(String[] args){
        // Creates the list to contain the fruits
        List<String> fruitlist = new ArrayList<>();
        //Adds the pears and apples and their base inventory at 0.
        fruitlist.add("pear");
        fruitlist.add("apple");
        inventories.put("apple", 0);
        inventories.put("pear", 0);
        for (int day = 1; day <= NUMBER_OF_DAYS; day++){
            int listlen = fruitlist.size();
            for (int i = 0; i < listlen; i++){
              String fruiter = fruitlist.get(i);
              //Creates the prices daily for each fruit in the fruitlist
              double priceloop2 = computePrice(BASE_PRICE, VARIATION);
              prices.put(fruiter, priceloop2);
            }
            System.out.println("Day: " + day + " out of 10");
            int choice;
            int amount;
            do{
                printMenu();
                choice = getChoice();
                switch (choice){
                    case 1: // Print cash balance and inventory
                        int listlen3 = fruitlist.size();
                        //Prints the inventory of each fruit and the current amount of cash.
                        for (int i = 0; i < listlen3; i++){
                            String fruiter3 = fruitlist.get(i);
                            String priceloop3 = currencyFormatter(prices.get(fruiter3));
                            System.out.println(fruiter3 + " inventory: " + inventories.get(fruiter3));
                        }
                        //Prints how much cash
                        System.out.println("Cash: " + currencyFormatter(cash));

                        break;
                    case 2: //Print today's prices
                        int listlen2 = fruitlist.size();
                        for (int i = 0; i < listlen2; i++){
                            String fruiter2 = fruitlist.get(i);
                            String priceloop = currencyFormatter(prices.get(fruiter2));
                            System.out.println("The price of " + fruiter2 + " is: " + priceloop);
                        }
                        break;
                    case 3: //Buy Fruit
                          System.out.println("What fruit do you want to buy?");
                          System.out.println("These are the available fruits " + fruitlist);
                          Scanner buykeyboard = new Scanner(System.in);
                          String buyfruit = buykeyboard.nextLine();
                          //Checks to make sure the fruit is on the market.
                          int listlen6 = fruitlist.size();
                          if(!checkFruits2(listlen6, fruitlist, buyfruit)){
                            System.out.println("That fruit is not on the market");
                            break;
                          }
                          else{
                            //Checks to make sure the user has enough money
                            amount = getQuantity(buyfruit, "buy");
                            if (!buyFruits(amount, buyfruit)) {
                                System.out.println("You don't have enough money.");
                              }
                              int add = inventories.get(buyfruit);
                              inventories.put(buyfruit, add);
                              break;
                          }

                    case 4: // Sell Fruit
                        System.out.println("What fruit do you want to sell?");
                        System.out.println("These are the available fruits " + fruitlist);
                        Scanner sellkeyboard = new Scanner(System.in);
                        String sellfruit = sellkeyboard.nextLine();
                        int listlen7 = fruitlist.size();
                        //Checks to see if the fruit is on the market.
                        if(!checkFruits2(listlen7, fruitlist, sellfruit)){
                          System.out.println("That fruit is not on the market");
                          break;
                        }
                        else{
                            //Checks to make sure you have enough fruit to sell.
                          amount = getQuantity(sellfruit, "sell");
                          if (!sellFruits(amount, sellfruit)){
                              System.out.println("You don't have enough " + sellfruit);
                            }
                            int sub = inventories.get(sellfruit);
                            inventories.put(sellfruit, sub);
                            break;
                        }
                    case 5: // Add Fruit
                        int listlen4 = fruitlist.size();
                        //Stops the method if there are too many fruits on the market already
                        if(listlen4 == 10){
                            System.out.println("There are too many fruits in the list");
                            break;
                        }
                        while (true){
                          System.out.println("What fruit do you want to add?");
                          System.out.println("These fruits are already being sold " + fruitlist);
                          Scanner addkeyboard = new Scanner(System.in);
                          String newfruit = addkeyboard.nextLine();
                          int listlen5 = fruitlist.size();
                          if (checkFruits(listlen5, fruitlist, newfruit)){
                            fruitlist.add(newfruit);
                            inventories.put(newfruit, 0);
                            prices.put(newfruit, computePrice(BASE_PRICE, VARIATION));
                            cash -= 10;
                            break;
                          }
                          System.out.println("Its not in the list, Try again: ");
                        }




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
        // Function that gets the price and current inventory of the fruit and sells a specific number of them, then updating the variables, most importantly giving the user more cash.
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
        // Function that gets the price and current inventory of the fruit and buys a specific number of them, then updating the variables.
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
    public static boolean checkFruits(int listlength, List<String> list, String fruit){
        for (int i = 0; i < listlength; i++){
            String oldfruit = list.get(i);
            if (fruit.equals(oldfruit)){
              return false;
            }

        }
        return true;


    }
    public static boolean checkFruits2(int listlength, List<String> list, String fruit){
        for (int i = 0; i < listlength; i++){
            String oldfruit = list.get(i);
            if (fruit.equals(oldfruit)){
              return true;
            }

        }
        return false;
  }
}
