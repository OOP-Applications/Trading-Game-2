import java.text.*;
import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class TradingGeme extends Application{
/**
These blocks of code generate the initial variables that will be used  throughout the game
*/
    static final int NUMBER_OF_DAYS = 10;
    static final double BASE_PRICE = 10;
    static final double VARIATION = 5;
    static final double INITIAL_CASH = 100;

    static double cash = INITIAL_CASH;
    //This creates the hashtables to be used to keep track of prices and inventories.
    static final Hashtable<String, List<Double>> NYprices = new Hashtable<String, List<Double>> ();
    static final Hashtable<String, List<Double>> LAprices = new Hashtable<String, List<Double>> ();
    static final Hashtable<String, Integer> inventories = new Hashtable<String, Integer> ();
    static final Hashtable<String, Integer> NYinventories = new Hashtable<String, Integer> ();
    static final Hashtable<String, Integer> LAinventories = new Hashtable<String, Integer> ();
    static final int day = 1;

/**
This is the main method, which
*/
    @Override
    public void start(Stage stage){
        List<String> fruitlist = new ArrayList<>();
        fruitlist.add("pear");
        fruitlist.add("apple");
        NYinventories.put("apple", (int) (20 + (Math.random() * 10)));
        NYinventories.put("pear", (int) (20 + (Math.random() * 10)));
        LAinventories.put("apple", (int) (20 + (Math.random() * 10)));
        LAinventories.put("pear", (int) (20 + (Math.random() * 10)));
        List<Double> appleNYlist = new ArrayList<>();
        List<Double> pearNYlist = new ArrayList<>();
        List<Double> appleLAlist = new ArrayList<>();
        List<Double> pearLAlist = new ArrayList<>();
        NYprices.put("apple", appleNYlist);
        NYprices.put("pear", pearNYlist);
        LAprices.put("apple", appleLAlist);
        LAprices.put("pear", pearLAlist);
        inventories.put("apple", 0);
        inventories.put("pear", 0);
        for (int day = 1; day <= NUMBER_OF_DAYS; day++){
            int listlen = fruitlist.size();
            System.out.println("Day: " + day + " out of 10");
            int choice;
            int amount;
            Scanner keyboard = new Scanner(System.in);
            for (int i = 0; i < listlen; i++){
                String fruiter = fruitlist.get(i);
                List<Double> newyorkprice = NYprices.get(fruiter);
                List<Double> losangelesprice = LAprices.get(fruiter);
                int NYlistlen4 = newyorkprice.size();
                int LAlistlen4 = losangelesprice.size();
                if(NYlistlen4 == 0 && LAlistlen4 == 0){
                    double newyorknewprice = computePrice(BASE_PRICE, VARIATION);
                    newyorkprice.add(newyorknewprice);
                    NYprices.put(fruiter, newyorkprice);
                    double losangelesnewprice = computePrice(BASE_PRICE, VARIATION);
                    losangelesprice.add(losangelesnewprice);
                    LAprices.put(fruiter, losangelesprice);
                }
                else{
                    double newyorkbaseprice = newyorkprice.get(NYlistlen4 - 1);
                    double losangelesbaseprice = losangelesprice.get(LAlistlen4 - 1);
                    double newyorknewprice = computePrice(newyorkbaseprice, VARIATION);
                    newyorkprice.add(newyorknewprice);
                    NYprices.put(fruiter, newyorkprice);
                    double losangelesnewprice = computePrice(losangelesbaseprice, VARIATION);
                    losangelesprice.add(losangelesnewprice);
                    LAprices.put(fruiter, losangelesprice);
                }
            }
            for (int i = 0; i <listlen; i++){
                int listlen12 = fruitlist.size();
                String fruiter2 = fruitlist.get(i);
                List<Double> newyorkprice = NYprices.get(fruiter2);
                List<Double> losangelesprice = LAprices.get(fruiter2);
                int NYlistlen = newyorkprice.size();
                int LAlistlen = losangelesprice.size();
                double NYdailyprice = newyorkprice.get(NYlistlen-1);
                System.out.println("The price of " + fruiter2 + " in New York is " + currencyFormatter(NYdailyprice));
                double LAdailyprice = losangelesprice.get(LAlistlen-1);
                System.out.println("The price of " + fruiter2 + " in Los Angeles is " + currencyFormatter(LAdailyprice));
                int NYcurrentinventory = NYinventories.get(fruiter2);
                System.out.println("There are " + NYcurrentinventory + " " + fruiter2 + "s in New York.");
                int LAcurrentinventory = LAinventories.get(fruiter2);
                System.out.println("There are " + LAcurrentinventory + " " + fruiter2 + "s in Los Angeles");
            }

            System.out.println("There is a $0.25 travel fee per fruit.");
            System.out.println("Would you like to trade in New York or Los Angeles? Enter 1 for NY or 2 for LA ");
            int location = keyboard.nextInt();
            int previous = 1;
            /*
            This section generates generates travel costs and sets prices based on which city the player has selected to travel to.
            It also checks for the previous location and if it changes it subtracts a $0.25 cent fee per fruit in the platers inventory.
            */
            if (location == 1){
              if (previous == 2){
                int listlen8 = fruitlist.size();
                int allfruits = totalinventory(listlen8, inventories, fruitlist);
                double fee = allfruits * 0.25;
                if (cash >= fee){
                    cash = cash - fee;
                    String cash_string = currencyFormatter(cash);
                    System.out.println("You have:" + cash_string);
                    previous = 1;
                }
              else{
                System.out.println("You don't have sufficient funds.");
                location = 2;
                previous = 2;
              }
              }
              else {
                previous = 1;
              }
            }
            else if (location == 2){
              if (previous == 1){
                int listlen8 = fruitlist.size();
                int allfruits = totalinventory(listlen8, inventories, fruitlist);
                double fee = allfruits * 0.25;
                if (cash >= fee){
                    cash = cash - fee;
                    String cash_string = currencyFormatter(cash);
                    System.out.println("You have:" + cash_string);
                    previous = 2;
                }
              else{
                System.out.println("You don't have sufficient funds.");
                location = 1;
                previous = 1;
              }
            }
            else {
              previous = 2;
            }
            }
            do{
                /**
                This code generates a menu, then performs the appropriate action based on the selection of the player
                */
                printMenu();
                choice = getChoice();
                switch (choice){
                    case 1: // Print cash balance and inventory
                    int listlen3 = fruitlist.size();
                    //Prints the inventory of each fruit and the current amount of cash.
                    for (int i = 0; i < listlen3; i++){
                        String fruiter3 = fruitlist.get(i);
                        System.out.println(fruiter3 + " inventory: " + inventories.get(fruiter3));
                        }
                    //Prints how much cash
                    System.out.println("Cash: " + currencyFormatter(cash));

                        break;
                    case 2: //Print today's prices
                        int listlen14 = fruitlist.size();
                        for (int i = 0; i <listlen14; i++){
                            String fruiter4 = fruitlist.get(i);
                            List<Double> newyorkprice = NYprices.get(fruiter4);
                            List<Double> losangelesprice = LAprices.get(fruiter4);
                            int NYlistlen2 = newyorkprice.size();
                            int LAlistlen2 = losangelesprice.size();
                            double NYdailyprice = newyorkprice.get(NYlistlen2 - 1);
                            System.out.println("The price of " + fruiter4 + " in New York is " + currencyFormatter(NYdailyprice));
                            double LAdailyprice = losangelesprice.get(LAlistlen2 - 1);
                            System.out.println("The price of " + fruiter4 + " in Los Angeles is " + currencyFormatter(LAdailyprice));
                        }
                        break;
                    case 3: //Print inventory of current city
                        int listlen13 = fruitlist.size();
                        if (location == 1){
                            for (int i = 0; i <listlen13; i++){
                                String fruiter5 = fruitlist.get(i);
                                System.out.println("There are " + NYinventories.get(fruiter5) + " " + fruiter5 + "s in New York");
                            }
                        }
                        else{
                            for (int i = 0; i <listlen13; i++){
                                String fruiter6 = fruitlist.get(i);
                                System.out.println("There are " + LAinventories.get(fruiter6) + " " + fruiter6 + "s in Los Angeles");
                            }
                        }
                        break;
                    case 4: //Buy Fruit
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
                            if (!buyFruits(amount, buyfruit, location, fruitlist)) {
                                System.out.println("You don't have enough money.");
                            }
                            break;
                        }
                    case 5: // Sell Fruit
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
                            if (!sellFruits(amount, sellfruit, location, fruitlist)){
                                System.out.println("You don't have enough " + sellfruit);
                            }
                            int sub = inventories.get(sellfruit);
                            inventories.put(sellfruit, sub);
                            break;
                        }
                    case 6: // Add Fruit
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
                            NYinventories.put(newfruit, (int) (20 + (Math.random() * 10)));
                            LAinventories.put(newfruit, (int) (20 + (Math.random() * 10)));
                            List<Double> NYnewfruitlist = new ArrayList<>();
                            List<Double> LAnewfruitlist = new ArrayList<>();
                            for(int i = 0; i < day; i++){
                              double zeroprice = 0.00;
                              NYnewfruitlist.add(zeroprice);
                              LAnewfruitlist.add(zeroprice);
                            }
                            NYnewfruitlist.add(computePrice(BASE_PRICE, VARIATION));
                            LAnewfruitlist.add(computePrice(BASE_PRICE, VARIATION));
                            NYprices.put(newfruit, NYnewfruitlist);
                            LAprices.put(newfruit, LAnewfruitlist);

                            cash -= 10;
                            break;
                            }
                            System.out.println("Its not in the list, Try again: ");
                        }
                }
            }while (choice != 7);
        }
        System.out.println("You finished with: " + currencyFormatter(cash));

        Scanner keyboard = new Scanner(System.in);
        /*
        XYChart.Series<Number,Number> series1 = new XYChart.Series<Number,Number>();
        XYChart.Series<Number,Number> series2 = new XYChart.Series<Number,Number>();

        List <Double> NYappleprices = NYprices.get("apple");
        List <Double> NYpearprices = NYprices.get("pear");
        List <Double> LAappleprices = LAprices.get("apple");
        List <Double> LApearprices = LAprices.get("pear");*/


        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();

        xAxis.setLabel("Day");
        yAxis.setLabel("Price (Dollars)");

        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);

        System.out.println("Would you like to graph New York fruits or Los Angleles fruits? (1 or 2)");
        int citychoice = keyboard.nextInt();
        int listlen15 = fruitlist.size();
        for (int i = 0; i< listlen15; i++){
            XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();
            String fruiter7 = fruitlist.get(i);
            if (citychoice == 1){
              List <Double> NYFruitPrices = NYprices.get(fruiter7);
              populate(series, NYFruitPrices);
              series.setName("NY " + fruiter7 + " Prices");
              lineChart.getData().add(series);
              stage.setTitle("New York Daily Prices");
            }
            else{
              List <Double> LAFruitPrices = LAprices.get(fruiter7);
              populate(series, LAFruitPrices);
              series.setName("LA " + fruiter7 + " Prices");
              lineChart.getData().add(series);
              stage.setTitle("Los Angeles Daily Prices");
            }
        }
        /*
        if (citychoice == 1){
            populate(series1, NYappleprices);
            populate(series2, NYpearprices);
            series1.setName("NY Apple Prices");
            series2.setName("NY Pear Prices");
            lineChart.getData().add(series1);
            lineChart.getData().add(series2);
            stage.setTitle("New York Daily Prices");
        } else{
            populate(series1, LAappleprices);
            populate(series2, LApearprices);
            series1.setName("LA Apple Prices");
            series2.setName("LA Pear Prices");
            lineChart.getData().add(series1);
            lineChart.getData().add(series2);
            stage.setTitle("Los Angeles Daily Prices");
        }
        */
        Scene scene = new Scene(lineChart,800,600);

        stage.setScene(scene);
        stage.show();
    }

/**
Prints the menu of options for the player to select, this accepts no inputs and outputs nothing
*/
    public static void printMenu(){
      System.out.println("1. Print cash balance and inventory");
      System.out.println("2. Print today's prices");
      System.out.println("3. Print inventory of the city.");
      System.out.println("4. Buy Fruit");
      System.out.println("5. Sell Fruit");
      System.out.println("6. Add Fruit");
      System.out.println("7. I am done for today");

    }

    public static int getChoice(){      /* getChoice takes in the users choice. The choice will correspond to the options presented in printMenu.
      By using a do-while loop, the prompt will always run once, and continune to run until an approate value is enterd*/
        Scanner keyboard = new Scanner(System.in);
        int choice;
        do{
            System.out.print("Your choice: ");
            choice = keyboard.nextInt();
        }
        while (choice > 7 || choice < 1);
        return choice;
    }

    public static String currencyFormatter(double amount){ // currencyFormatter turns a given amounts of money into the appropriate formate, the way prices are noramlly displayed.
      DecimalFormat myFormatter = new DecimalFormat("$###,###.00");
      return myFormatter.format(amount);
    }

    public static double computePrice(double basePrice, double variation){
        /*
        This function is used to vary the price fruit.

        Input:
            basePrice(double) - The price from the previous day which will be
            used as the price that is going to be changed.
            variation(double) - The variation represents the maximum amount the
            price can change form the previous day.

        Output:
            newPrice(double) - The new price once the variation has been added
            or subtracted.
        */
        double crashChance = 0.01;
        double newPrice = 0;
        double chance = Math.random();
        double upChance = 0;
        /*The string of if statements comparing basePrice to an integer allows
        the prices to have higher and lower chances of going up based on how
        high the previous day's price was, as well as implement a chance for the
        market to crash once prices get very very high.
        */
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
        /* The following if statements calculate newPrice by adding or
        subracting a random variation.
        */
        if (chance <= upChance || basePrice <= 0.5){
            newPrice = basePrice + (Math.random() * variation);
        }
        if (chance > upChance){
            newPrice = basePrice - (Math.random() * variation);
        }
        if (newPrice < 0.5){
            newPrice = 0.5;
        }
        // The market crash drops the price to between $1 and $3.
        if (Math.random() <= crashChance){
            newPrice = Math.random() * 2 + 1;
            System.out.println("The market has crashed!");
        }
        return(newPrice);
    }

    public static int getQuantity(String product, String action){ //getQuantity asks the player for a quantity of a certain item. This method can be used for many purposes.
        System.out.print("\nHow many " + product + " do you want to " + action + "? ");
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextInt();
        }

    public static boolean sellFruits(int amount, String fruit, int location, List<String> list){
        int inventory = inventories.get(fruit);
        if (amount > inventory){
            return false;
        }
        if (location == 1){
            int NYinventory = NYinventories.get(fruit);
            int listlen11 = list.size();
            List<Double> newyorkprice4 = NYprices.get(fruit);
            int NYlistlen5 = newyorkprice4.size();
            double NYdailyprice4 = newyorkprice4.get(NYlistlen5-1);
            cash = cash + amount * NYdailyprice4;
            inventory -= amount;
            inventories.put(fruit, inventory);
            NYinventory += amount;
            NYinventories.put(fruit, NYinventory);
            return true;
        }
        else{
            int LAinventory = LAinventories.get(fruit);
            int listlen10 = list.size();
            List<Double> losangelesprice4 = LAprices.get(fruit);
            int LAlistlen5 = losangelesprice4.size();
            double LAdailyprice4 = losangelesprice4.get(LAlistlen5-1);
            cash = cash + amount * LAdailyprice4;
            inventory -= amount;
            inventories.put(fruit, inventory);
            LAinventory += amount;
            LAinventories.put(fruit, LAinventory);
            return true;
        }
    }

    public static boolean buyFruits(int amount, String fruit, int location, List<String> list){
        // Function that gets the price and current inventory of the fruit and buys a specific number of them, then updating the variables.
        if (location == 1){
            if (NYinventories.get(fruit) < amount){
                System.out.println("There are no more " + fruit + " in this city.");
                return false;
            }
            else{
                int inventory = NYinventories.get(fruit);
                int listlen9 = list.size();
                List<Double> newyorkprice2 = NYprices.get(fruit);
                int NYlistlen3 = newyorkprice2.size();
                double NYdailyprice2 = newyorkprice2.get(NYlistlen3 - 1);
                if (amount * NYdailyprice2 < cash){
                    cash-= amount * NYdailyprice2;
                    inventory -= amount;
                    NYinventories.put(fruit, inventory);
                    int userinventory = inventories.get(fruit);
                    userinventory += amount;
                    inventories.put(fruit, userinventory);
                    return true;
                }
                else{
                    System.out.println("You do not have enough money to proceed with this transaction.");
                    return false;
                }
            }
        }
        else{
            if (LAinventories.get(fruit) < amount){
                System.out.println("There are no more " + fruit + " in this city.");
                return false;
            }
            else{
              int inventory = LAinventories.get(fruit);
              int listlen10 = list.size();
              List<Double> losangelesprice2 = LAprices.get(fruit);
              int LAlistlen3 = losangelesprice2.size();
              double LAdailyprice2 = losangelesprice2.get(LAlistlen3 - 1);
                if (amount * LAdailyprice2 < cash){
                    cash-= amount * LAdailyprice2;
                    inventory -= amount;
                    LAinventories.put(fruit, inventory);
                    int userinventory2 = inventories.get(fruit);
                    userinventory2 += amount;
                    inventories.put(fruit, userinventory2);
                    return true;
                }
                else{
                    System.out.println("You do not have enough money to proceed with this transaction.");
                    return false;
                }
            }
        }
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
    public static int totalinventory(int listlength, Hashtable<String, Integer> hashtable, List<String> list){
        int number = 0;
        for(int i = 0; i<listlength; i++){
            String fruit = list.get(i);
            int fruitcount = hashtable.get(fruit);
            number += fruitcount;
        }
        return number;
    }
    public static void populate(XYChart.Series<Number,Number> series, List<Double> list){
        for(int i = 0; i <10; i++){
            series.getData().add(new XYChart.Data<>(i+1, list.get(i)));
        }
    }

    public static void main(String[] args){
        Application.launch(args);
    }

}
