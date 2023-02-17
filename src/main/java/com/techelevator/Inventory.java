package com.techelevator;

import com.techelevator.util.TELog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    /** PROPERTIES */
    private final Map<String, String> itemCategoryMap = new HashMap<>();
    private final Map<String, String> itemSelectedMap = new HashMap<>();
    private final String inventoryFile = "capstone/vendingmachine.csv";
    int unitsRemaining;
    /*  getCanonicalPath()
     *  platform specific method: directory path needs to conform to Unix:MacOS (/) or PC (\\)
     *  private final String inventoryFile = "C:\\Users\\18325\\Desktop\\CapStone Backup\\capstone\\vendingmachine.csv";
     *  private final String inventoryFile = "/Users/chexpeare/MeritAmerica/PairProgrammingBackup/capstone/vendingmachine.csv";
     */

    /** CONSTRUCTOR */
    public Inventory() {
    }

    /** METHODS */
    public void displayInventory() throws IOException {
        System.out.println("\n=========================\n"
                + "  \33[34;1mTHE UMBRELLA ACADEMY\33[0m\n"
                + "        INVENTORY\n"
                + "-------------------------");

        try {
        File inputFile = new File(inventoryFile);
        String canonical = inputFile.getCanonicalPath();
        FileReader readerFile = new FileReader(canonical);
        BufferedReader reader = new BufferedReader(readerFile);
        String currentLine = reader.readLine();


                while (currentLine != null) {
                    String[] splitItemLine = currentLine.split("\\|");
                    System.out.print(   "\033[33m" + splitItemLine[0] + "\033[32m" + "|" +
                            "\033[34m" + splitItemLine[1] + "\033[32m" + "|" +
                            "\033[35m" + splitItemLine[2] + "\033[32m" + "|" +
                            "\033[32m" + splitItemLine[3] + "\033[38m");

    //          INVENTORY: CHIPS
                if(splitItemLine[3].equals("Chip")) {
                    Chips chips = new Chips();

                    switch (splitItemLine[1]) {
                        case "Potato Crisps":
                            unitsRemaining = chips.getPotatoCrispsLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Stackers":
                            unitsRemaining = chips.getStackersLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Grain Waves":
                            unitsRemaining = chips.getGrainWavesLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Cloud Popcorn":
                            unitsRemaining = chips.getCloudPopcornLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                    }
                }

    //          INVENTORY: CANDY
                if (splitItemLine[3].equals("Candy")) {
                    Candy candy = new Candy();

                    switch (splitItemLine[1]) {
                        case "Moonpie":
                            unitsRemaining = candy.getMoonpiesLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Cowtales":
                            unitsRemaining = candy.getCowtalesLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Wonka Bar":
                            unitsRemaining = candy.getWonkaBarsLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Crunchie":
                            unitsRemaining = candy.getCrunchiesLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                    }
                }

    //          INVENTORY: DRINKS
                if(splitItemLine[3].equals("Drink")) {
                    Drinks drinks = new Drinks();

                    switch (splitItemLine[1]) {
                        case "Cola":
                            unitsRemaining = drinks.getColaLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Dr. Salt":
                            unitsRemaining = drinks.getDrSaltLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Mountain Melter":
                            unitsRemaining = drinks.getMountainMelterLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Heavy":
                            unitsRemaining = drinks.getHeavyLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                    }
                }

    //          INVENTORY: GUM
                if(splitItemLine[3].equals("Gum")) {
                    Gum gum = new Gum();

                    switch (splitItemLine[1]) {
                        case "U-Chews":
                            unitsRemaining = gum.getuChewsLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Little League Chew":
                            unitsRemaining = gum.getLittleLeaugeChewLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Chiclets":
                            unitsRemaining = gum.getChicletsLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                        case "Triplemint":
                            unitsRemaining = gum.getTriplemintLeft();
                            totalRemainingColor(unitsRemaining);
                            break;
                    }
                }
                currentLine = reader.readLine();
            }
        } catch (IOException e) {
            System.out.println("The item selected is not in our inventory.");
        } finally {
        }
        System.out.println("-------------------------\n"
                + "  \33[34;1mVENDO-MATIC 800\33[0m\n"
                + "=========================");
        System.out.println("\033[0m");     // Return text color to white
    }

    public void getInventory() throws IOException{
        BufferedReader reader = null;
        String currentLine = null;
        try {
            File inputFile = new File(inventoryFile);
            String canonical = inputFile.getCanonicalPath();
            FileReader readerFile = new FileReader(canonical);
            reader = new BufferedReader(readerFile);
            currentLine = reader.readLine();
        } catch (IOException e) {
            System.out.println("\033[31;1mUnable to find the inventory file. " +
                    "           \nPlease confirm that the file exists in the specified directory.\033[0m");
        } finally {
            TELog.log("Unable to find the inventory file.");
        }

        while(currentLine != null) {
            String[] splitItemLine = currentLine.split("\\|");
            itemCategoryMap.put(splitItemLine[0], splitItemLine[3]);        // Code & Category Selection
            itemSelectedMap.put(splitItemLine[0], splitItemLine[1]);    // Code & Item Selection
            currentLine = reader.readLine();
        }
    }

    private void totalRemainingColor(int unitsRemaining) {
        if (unitsRemaining == 5) {
            System.out.println(" | " + "\033[0;1m" + unitsRemaining + "\033[0m");
        } else if (unitsRemaining == 0) {
            System.out.println(" | " + "\033[31;1m" + unitsRemaining + "   SOLD OUT" + "\033[0m");
        } else {
            System.out.println(" | " + "\033[33;1m" + unitsRemaining + "\033[0m");
        }

    }

    public Map<String, String> getItemSelectedMap() {
        return itemSelectedMap;
    }
    public Map<String, String> getItemCategoryMap() {
        return itemCategoryMap;
    }

}




