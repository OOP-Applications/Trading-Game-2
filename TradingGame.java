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
    static double currentApplePrice, currentPearPrice;

    static final Hashtable<String, Integer> prices = new Hashtable<String, Integer> ();

    public static void main(String[] args){
        currentApplePrice = BASE_PRICE;
        currentPearPrice = BASE_PRICE;
        for (int day = 1; day <= NUMBER_OF_DAYS; day++){
            currentApplePrice = computePrice(currentApplePrice, VARIATION);
            currentPearPrice = computePrice(currentPearPrice, VARIATION);
            System.out.println("Day: " + day + " out of 10");
            int choice;
            int amount;
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
                        currencyFormatter(currentApplePrice));
                        System.out.println("The price of pears is: " +
                        currencyFormatter(currentPearPrice));
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
        double crashChance = 0.01;
        double newPrice = 0;
        double chance = Math.random();
        double upChance = 0;
        if (basePrice <= 1){
            upChance = 0.95;
            crashChance = 0.00;
        }
        else if (basePrice <= 2){
            upChance = 0.9;
            crashChance = 0.00;
        }
        else if (basePrice <= 3.5){
            upChance = 0.8;
            crashChance = 0.00;
        }
        else if (basePrice <= 5){
            upChance = 0.7;
            crashChance = 0.00;
        }
        else if (basePrice <= 7.5){
            upChance = 0.6;
            crashChance = 0.005;
        }
        else if (basePrice <= 10){
            upChance = 0.5;
            crashChance = 0.01;
        }
        else if (basePrice <= 12.5){
            upChance = 0.4;
            crashChance = 0.05;
        }
        else if (basePrice <= 15){
            upChance = 0.3;
            crashChance = 0.075;
        }
        else if (basePrice <= 16.5){
            upChance = 0.2;
            crashChance = 0.1;
        }
        else if (basePrice <= 18){
            upChance = 0.1;
            crashChance = 0.125;
        }
        else if (basePrice <= 19){
            upChance = 0.05;
            crashChance = 0.15;
        }
        else if (basePrice > 19){
            upChance = 0.01;
            crashChance = 0.3;
        }
        if (chance <= upChance || basePrice <= 0.5){
            newPrice = basePrice + (Math.random() * variation);
        }
        if (chance > upChance){
            newPrice = basePrice - (Math.random() * variation);
        }
        if (newPrice < 0.5){
            newPrice = 0.5;
        }
        if (Math.random() <= crashChance){
            newPrice = Math.random() * 2 + 1;
            System.out.println("The market has crashed!");
        }
        return(newPrice);
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
        cash += amount * currentApplePrice;
        appleInventory -= amount;
        return true;
    }

    public static boolean sellPears(int amount){
        if (amount > pearInventory) {
            return false;
        }
        cash += amount * currentPearPrice;
        pearInventory -= amount;
        return true;
    }

    public static boolean buyApples(int amount){
        if (amount * currentApplePrice <= cash) {
            cash -= amount * currentApplePrice;
            appleInventory += amount;
            return true;
        }
        return false;
    }

    public static boolean buyPears(int amount){
        if (amount * currentPearPrice <= cash) {
            cash -= amount * currentPearPrice;
            pearInventory += amount;
            return true;
        }
        return false;

    }
}
