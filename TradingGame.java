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

            System.out.println("\nThe prices for New York are "+currencyFormatter(NYapplePrice)+" for apples and "+currencyFormatter(NYpearPrice)+" for pears.");
            System.out.println("The prices in Los Angeles are "+currencyFormatter(LAapplePrice)+" for apples and "+currencyFormatter(LApearPrice)+" for pears.\n");
            System.out.println("There are "+NYappleinventory+" apples and "+NYpearinventory+" pears in New York.");
            System.out.println("There are "+LAappleinventory+" apples and "+LApearinventory+" pears in Los Angeles.\n");
            System.out.println("There is a $15.00 plus a $0.20 per fruit fee for travel. Your first location is free.");
            System.out.println("Would you like to trade in New York or Los Angeles? Enter 1 for NY or 2 for LA ");
            int location;

            if(applePrice == NYapplePrice){
              location = keyboard.nextInt();
              if (location == 1){
                  applePrice = NYapplePrice;
                  pearPrice = NYpearPrice;
              } else {
                int fruit = appleInventory + pearInventory;
                double fee = 15.00 + (fruit * 0.2);
                cash = cash - fee;
                System.out.println("Your balance is:" + cash);
                applePrice = LAapplePrice;
                pearPrice = LApearPrice;
              }
            } else if (applePrice == LAapplePrice){
              location = keyboard.nextInt();
              if (location == 2){
                applePrice = LAapplePrice;
                pearPrice = LApearPrice;
              } else {
                int fruit = appleInventory + pearInventory;
                double fee = 15.00 + (fruit * 0.2);
                cash = cash - fee;
                System.out.println("Your balance is:" + cash);
                applePrice = NYapplePrice;
                pearPrice = NYpearPrice;
              }
            } else {
              location = keyboard.nextInt();
              if (location == 1){
                  applePrice = NYapplePrice;
                  pearPrice = NYpearPrice;
              } else {
                applePrice = LAapplePrice;
                pearPrice = LApearPrice;
              }
            }



            do{
                printMenu();
                choice = getChoice();
                switch (choice){
                    case 1: // Print cash balance and inventory
                        System.out.println("\nCash: " + currencyFormatter(cash));
                        System.out.println("Apple inventory: " + appleInventory);
                        System.out.println("Pear inventory: " + pearInventory+ "\n");

                        break;
                    case 2: //Print today's prices
                        System.out.println("\nThe price of apples is: " +
                        currencyFormatter(applePrice));
                        System.out.println("The price of pears is: " +
                        currencyFormatter(pearPrice)+"\n");
                        break;
                    case 3: //Print inventory of current city
                        if (location == 1){
                            System.out.println("\nThere are "+NYappleinventory+" apples and "+NYpearinventory+" pears here in New York.\n");
                        } else{
                            System.out.println("\nThere are "+LAappleinventory+" apples and "+LApearinventory+" pears here in Los Angeles.\n");
                        }
                        break;
                    case 4: //Buy apples
                        if (location == 1){
                            System.out.println("\nThere are "+NYappleinventory+" apples here in New York for "+currencyFormatter(applePrice)+" each.");
                        } else{
                            System.out.println("\nThere are "+LAappleinventory+" apples here in Los Angeles for "+currencyFormatter(applePrice)+" each.");
                        }
                        System.out.println("You have "+currencyFormatter(cash));
                        amount = getQuantity("apples", "buy");
                        if (!buyApples(amount, location)) {
                            System.out.println("\nYou don't have enough money.\n");
                        }
                        break;
                    case 5: // Sell apples
                        System.out.println("\nYou have "+appleInventory+" apples that you can sell for "+currencyFormatter(applePrice)+" each.");
                        amount = getQuantity("apples", "sell");
                        if (!sellApples(amount, location)){
                            System.out.println("\nYou don't have enough apples.\n");
                        }
                        break;
                    case 6: { // Buy buyPears
                        if (location == 1){
                            System.out.println("\nThere are "+NYpearinventory+" pears here in New York for "+currencyFormatter(pearPrice)+" each.");
                        } else{
                            System.out.println("\nThere are "+LApearinventory+" pears here in Los Angeles for "+currencyFormatter(pearPrice)+" each.");
                        }
                        System.out.println("You have "+currencyFormatter(cash));
                        amount = getQuantity("pears", "buy");
                        if (!buyPears(amount, location)){
                          System.out.println("\nYou don't have enough money\n");
                          }
                        break;
                        }
                    case 7: { // Sell Pears
                        System.out.println("\nYou have "+pearInventory+" pears that you can sell for "+currencyFormatter(pearPrice)+" each.");
                        amount = getQuantity("pears", "sell");
                        if (!sellPears(amount, location) ) {
                          System.out.println("\nYou dont have enough pears\n");
                          }
                        break;
                    }
                }
            }while (choice != 8);
        }
        System.out.println("You finished with: " + currencyFormatter(cash));

    }

    public static void printMenu(){
      System.out.println("1. Print cash balance and inventory");
      System.out.println("2. Print today's prices");
      System.out.println("3. Print inventory of the city.");
      System.out.println("4. Buy apples");
      System.out.println("5. Sell Apples");
      System.out.println("6. Buy pears");
      System.out.println("7. Sell pears");
      System.out.println("8. I am done for today");

    }

    public static int getChoice(){
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do {
            System.out.print("Your choice: ");
            choice = keyboard.nextInt();
        } while (choice > 8 || choice < 1);
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
        System.out.print("\nHow many " + product + " do you want to " + action + "? ");
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
        }

    public static boolean sellApples(int amount, int location){
        if (amount > appleInventory) {
            return false;
        }
        cash += amount * applePrice;
        appleInventory -= amount;
        if (location == 1){
            NYappleinventory += amount;
        } else{
            LAappleinventory += amount;
            }
            return true;
        }

    public static boolean sellPears(int amount, int location){
        if (amount > pearInventory) {
            return false;
        }
        cash += amount * pearPrice;
        pearInventory -= amount;
        if (location == 1){
            NYpearinventory += amount;
        } else{
            LApearinventory += amount;
        }
            return true;
        }

    public static boolean buyApples(int amount, int location){
        if (amount * applePrice < cash) {
            cash -= amount * applePrice;
            appleInventory += amount;
            if (location == 1){
                if (NYappleinventory > 0){
                    NYappleinventory -= amount;
                } if (NYappleinventory == 0){
                    System.out.println("There are no apples left in New York.");
                }
            } else{
                if (LAappleinventory > 0){
                    LAappleinventory -= amount;
                } if (LAappleinventory == 0){
                        System.out.println("There are no apples left in Los Angeles. ");
                }
            }
            return true;
        }
        return false;
    }

    public static boolean buyPears(int amount, int location){

        if (amount * pearPrice < cash) {
            cash -= amount * pearPrice;
            pearInventory += amount;
            if (location == 1){
                if (NYpearinventory > 0){
                    NYpearinventory -= amount;
                } if (NYpearinventory == 0){
                    System.out.println("There are no pears left in New York.");

                    }

            } else{
                if (LApearinventory > 0){
                    LApearinventory -= amount;
                } if (LApearinventory == 0){
                    System.out.println("There are no pears left in Los Angeles. ");

                    }

            }
            return true;
        }
        return false;

    }
}
