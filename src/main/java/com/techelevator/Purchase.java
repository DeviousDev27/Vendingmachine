package com.techelevator;

import java.io.*;

import com.techelevator.util.SalesReport;
import com.techelevator.util.TELog;
import com.techelevator.view.Menu;
import java.util.Scanner;
import java.util.Date;

/** Purchase.java - Pushed from Backup */
public class Purchase {
    private static final String MAIN_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String MAIN_MENU_OPTION_SELECT_PRODUCT = "Select Product";
    private static final String MAIN_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_FEED_MONEY,
            MAIN_MENU_OPTION_SELECT_PRODUCT ,
            MAIN_MENU_OPTION_FINISH_TRANSACTION};
//    private static final String logFilePath = "capstone/log.txt";
    private static final Date date = new Date();
    private static double totalSales = 0.0;
    private static int chipsCounter = 0;
    private static int candyCounter = 0;
    private static int drinksCounter = 0;
    private static int gumCounter = 0;
    private final Menu menu;
    private static String itemSelected;
    private String itemCategory;
    private String itemKey;
    //    private String lowKey;
    private float balance;
    private double change;
    private int nickels;
    private int dimes;
    private int quarters;

    protected double itemPrice = 0.0;

    protected double deposit;
    protected int itemsRemaining;
    protected String soldOutString = "\033[31m" + "\033[1m" + " SOLD OUT." + "\033[0m"   + "\033[38m" +
                                    "\nPlease select another product or press [3] to finish your transaction.";
    protected String addFundsString = "Please add additional funds or press [3] to finish your transaction.";

    /** CONSTRUCTOR */
    public Purchase (Menu menu) {
        this.balance =0;
        this.change = 0;
        this.menu = menu;
    }

    public void run () throws IOException {
        while (true) {
            System.out.printf('\n' + "Current Balance: $" + "%.2f", getCurrentBalance());
//            System.out.println("\n");
            String choice = (String)menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            switch (choice) {
                case MAIN_MENU_OPTION_FEED_MONEY:
                    feedMoney();
                    break;
                case MAIN_MENU_OPTION_SELECT_PRODUCT:
                    selectProduct(new Inventory());
                    break;
                case MAIN_MENU_OPTION_FINISH_TRANSACTION:
                    finishTransaction();
                    return;
            }
        }
    }

    /** feedMoney() */
    public void feedMoney() throws IOException {

        try {
            Scanner userInput = new Scanner(System.in);
            System.out.print("How much money would you like to deposit: ");
            deposit = userInput.nextDouble();
//        System.out.println("Current Money Provided: $" + deposit);
            if (deposit > 0) {
                balance += deposit;
                TELog.log("FEED MONEY: $"+ deposit + "  " + "$" + balance);
            } else if (deposit <= 0) {
                System.out.println("\033[31;1mPlease enter a valid amount.\033[0m");
            }
        } catch (Exception e) {
            System.out.println("\033[31;1mPlease enter a valid amount.\033[0m");
        } finally {
            TELog.log("INVALID DEPOSTI");
        }
    }

    /** START: selectProduct() */
    public void selectProduct(Inventory inventory) throws IOException {
        inventory.getInventory();
        Scanner userInput = new Scanner(System.in);
        System.out.print("Item to purchase (Enter product key): ");
        itemKey = userInput.nextLine();
//        lowKey = itemKey.toLowerCase();

        if ( !(itemKey.equals("A1") || itemKey.equals("A2") ||itemKey.equals("A3") ||itemKey.equals("A4")
                ||itemKey.equals("B1") ||itemKey.equals("B2") ||itemKey.equals("B3") ||itemKey.equals("B4")
                ||itemKey.equals("C1") ||itemKey.equals("C2") ||itemKey.equals("C3") ||itemKey.equals("C4")
                ||itemKey.equals("D1") ||itemKey.equals("D2") ||itemKey.equals("D3") ||itemKey.equals("D4")) ) {

            System.out.println("\033[31;1m\nInvalid product code. Please try again.\033[0m");
            itemKey =null;
            itemSelected = null;
            itemCategory = null;
            return;
        }

        itemSelected = inventory.getItemSelectedMap().get(itemKey);
        itemCategory = inventory.getItemCategoryMap().get(itemKey);

//        double itemPrice = 0.0;
        switch (itemCategory) {
            // Start of Chips
            case "Chip":
                Chips chips = new Chips();
                switch (itemSelected) {
                    case "Potato Crisps": // A1
                        itemPrice = chips.getPotatoCrispsPrice();
                        if ( (balance >= chips.getPotatoCrispsPrice()) && (chips.getPotatoCrispsLeft() > 0) ) {
                            chipsCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = chips.completeChipsPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (chips.getPotatoCrispsLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (chips.getPotatoCrispsLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        break;
                    case "Grain Waves":
                        itemPrice = chips.getGrainWavesPrice();
                        if ( (balance >= chips.getGrainWavesPrice()) && (chips.getGrainWavesLeft() > 0) ) {
                            chipsCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = chips.completeChipsPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (chips.getGrainWavesLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (chips.getGrainWavesLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        break;
                    case "Stackers":
                        itemPrice = chips.getStackersPrice();
                        if ( (balance >= chips.getStackersPrice()) && (chips.getStackersLeft() > 0) ) {
                            chipsCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = chips.completeChipsPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (chips.getStackersLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (chips.getStackersLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        break;
                    case "Cloud Popcorn":
                        itemPrice = chips.getCloudPopcornPrice();
                        if ( (balance >= chips.getCloudPopcornPrice()) && (chips.getCloudPopcornLeft() > 0) ) {
                            chipsCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = chips.completeChipsPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (chips.getCloudPopcornLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (chips.getCloudPopcornLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + chipsCounter + "|" + totalSales);
                        break;
                }
                break;

            // Start of Candy
            case "Candy":
                Candy candy = new Candy();
                switch (itemSelected) {
                    case "Moonpie":
                        itemPrice = candy.getMoonPiePrice();
                        if ( (balance >= candy.getMoonPiePrice()) && (candy.getMoonpiesLeft() > 0) ) {
                            candyCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = candy.completeCandyPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (candy.getMoonpiesLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (candy.getMoonpiesLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        break;
                    case "Cowtales":
                        itemPrice = candy.getCowtalesPrice();
                        if ( (balance >= candy.getCowtalesPrice()) && (candy.getCowtalesLeft() > 0) ) {
                            candyCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = candy.completeCandyPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (candy.getCowtalesLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (candy.getCowtalesLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        break;
                    case "Crunchie":
                        itemPrice = candy.getCrunchiesPrice();
                        if ( (balance >= candy.getCrunchiesPrice()) && (candy.getCrunchiesLeft() > 0) ) {
                            candyCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = candy.completeCandyPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (candy.getCrunchiesLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (candy.getCrunchiesLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        break;
                    case "Wonka Bar":
                        itemPrice = candy.getWonkaBarsPrice();
                        if ( (balance >= candy.getWonkaBarsPrice()) && (candy.getWonkaBarsLeft() > 0) ) {
                            candyCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = candy.completeCandyPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (candy.getWonkaBarsLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (candy.getWonkaBarsLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + candyCounter + "|" + totalSales);
                        break;
                }
                break;

            //Start of Drinks
            case "Drink":
                Drinks drinks = new Drinks();
                switch (itemSelected) {
                    case "Cola":
                        itemPrice = drinks.getColaPrice();
                        if ( (balance >= drinks.getColaPrice()) && (drinks.getColaLeft() > 0) ) {
                            drinksCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = drinks.completeDrinksPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (drinks.getColaLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (drinks.getColaLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        break;
                    case "Dr. Salt":
                        itemPrice = drinks.getDrSaltPrice();
                        if ( (balance >= drinks.getDrSaltPrice()) && (drinks.getDrSaltLeft() > 0) ) {
                            drinksCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = drinks.completeDrinksPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (drinks.getDrSaltLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (drinks.getDrSaltLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        break;
                    case "Mountain Melter":
                        itemPrice = drinks.getMountainMelterPrice();
                        if ( (balance >= drinks.getMountainMelterPrice()) && (drinks.getMountainMelterLeft() > 0) ) {
                            drinksCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = drinks.completeDrinksPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (drinks.getMountainMelterLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (drinks.getMountainMelterLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        break;
                    case "Heavy":
                        itemPrice = drinks.getHeavyPrice();
                        if ( (balance >= drinks.getHeavyPrice()) && (drinks.getHeavyLeft() > 0) ) {
                            drinksCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = drinks.completeDrinksPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (drinks.getHeavyLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (drinks.getHeavyLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + drinksCounter + "|" + totalSales);
                        break;
                }
                break;

            // Start of Gum
            case "Gum":
                Gum gum = new Gum();
                switch (itemSelected) {
                    case "U-Chews":
                        itemPrice = gum.getUChewsPrice();
                        if ( (balance >= gum.getUChewsPrice()) && (gum.getuChewsLeft() > 0) ) {
                            gumCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = gum.completeGumPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (gum.getuChewsLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (gum.getuChewsLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        break;
                    case "Little League Chew":
                        itemPrice = gum.getLittleLeaugeChewPrice();
                        if ( (balance >= gum.getLittleLeaugeChewPrice()) && (gum.getLittleLeaugeChewLeft() > 0) ) {
                            gumCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = gum.completeGumPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (gum.getLittleLeaugeChewLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (gum.getLittleLeaugeChewLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        break;
                    case "Chiclets":
                        itemPrice = gum.getChicletsPrice();
                        if ( (balance >= gum.getChicletsPrice()) && (gum.getChicletsLeft() > 0) ) {
                            gumCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = gum.completeGumPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (gum.getChicletsLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (gum.getChicletsLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        break;
                    case "Triplemint":
                        itemPrice = gum.getTriplemintPrice();
                        if ( (balance >= gum.getTriplemintPrice()) && (gum.getTriplemintLeft() > 0) ) {
                            gumCounter++;
                            TELog.log(itemKey + "|" + itemSelected + "|" + "Current Balance: " + "$"+ balance  + "|"
                                    + "New Balance: $" + (balance - itemPrice));
                            balance -= itemPrice;
                            totalSales += itemPrice;
                            itemsRemaining = gum.completeGumPurchase(itemSelected);
                            itemsRemainingFormat();
                        } else if ( (balance < itemPrice) && (gum.getTriplemintLeft() > 0) ) {
                            addFundsFormat();
                        } else if ( (balance >= itemPrice) && (gum.getTriplemintLeft() == 0) ) {
                            soldOutFormat();
                        }
                        SalesReport.log(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        SalesReport.appendToSalesReport(itemSelected + "|" + itemPrice + "|" + gumCounter + "|" + totalSales);
                        break;
                }
                break;
        }
    }
    /** END: selectProduct() */

    public void finishTransaction() throws IOException {
        nickels =0;
        dimes = 0;
        quarters = 0;
        double totalChange =0;
        double quarterRemainder;
        double dimesRemainder;
        TELog.log("GIVE CHANGE: $" + balance);

        if (balance > 0) {
            totalChange = balance;
            change = balance;
            balance =0;
        }

        totalChange = Math.ceil(totalChange * 100);
        quarterRemainder = totalChange%25;
        quarters = (int)(totalChange/25);
        dimesRemainder = quarterRemainder%10;
        dimes = (int)(quarterRemainder/10);
        nickels = (int)(dimesRemainder/5);
        totalChange /= 100;

        System.out.println("\nTransaction Completed" + '\n');
        System.out.printf("\033[32m" + "Your change is " + "$%.2f" + "\033[38m" + '\n' , totalChange);
        System.out.println("Quarters: " + quarters + "   " + "Dimes "+dimes+"   " +"Nickels " + nickels + "\n");

        for (int i = 0; i < candyCounter; i++) {
            System.out.println("Munch Munch, Yum!");
        }
        for (int i = 0; i < chipsCounter; i++) {
            System.out.println("Crunch Crunch, Yum!");
        }
        for (int i = 0; i < gumCounter; i++) {
            System.out.println("Chew Chew, Yum!");
        }
        for (int i = 0; i < drinksCounter; i++) {
            System.out.println("Glug Glug, Yum!");
        }
        candyCounter = 0;
        drinksCounter = 0;
        gumCounter = 0;
        chipsCounter = 0;
    }

    /** GETTERS */
    public double getCurrentBalance() {
        return balance;
    }
    public double getTotalSales() {
        return totalSales;
    }
    public double getChange() {
        return change;
    }
    public String getItemKey() {
        return itemKey;
    }
    public String getItemCategory() {
        return itemCategory;
    }
    public static String getItemSelected() {
        return itemSelected;
    }
    public int getNickels() {
        return nickels;
    }
    public int getDimes() {
        return dimes;
    }
    public int getQuarters() {
        return quarters;
    }
    public void itemsRemainingFormat() {
        System.out.println(lineOfEquals());
        System.out.println(itemSelected + " remaining: " + itemsRemaining);
        System.out.println(lineOfEquals());
    }
    public void soldOutFormat() {
        System.out.println(lineOfEquals());
        System.out.println(itemSelected + soldOutString);
        System.out.println(lineOfEquals());
    }
    public void addFundsFormat() {
        System.out.println(lineOfEquals());
        System.out.println(addFundsString);
        System.out.println(lineOfEquals());
    }
    public String lineOfEquals() {
        return "======================================================================";
    }

}
