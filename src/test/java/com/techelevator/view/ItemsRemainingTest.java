package com.techelevator.view;

import com.techelevator.Candy;
import com.techelevator.Chips;
import com.techelevator.Drinks;
import com.techelevator.Gum;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

class ItemsRemainingTest extends TestCase {

    /**
     * Test completeXXXXXXPurchase() method in: Chips, Candy, Drinks, Gum
     * Outer forLoop: buys an item until it runs out from a starting stock of 5
     * Inner forLoop: goes through array[index] of items
     *
     */
    @Test
    void test_Complete_Candy_Purchase_Items_Remaining() {
        Candy candy = new Candy();
        String[] input = {"Moonpie", "Cowtales", "Wonka Bar", "Crunchie", "\n", "Donut", "dhg345", "-1"};

        for ( int i = 4; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                int remaining = candy.completeCandyPurchase(input[j]);
                if (remaining != 8951423) {
                    Assert.assertEquals("Test input:<" + input[j] + "> ", i, remaining);
//                System.out.println("Remaining: " + i  + " Expected: " + remaining + " | " + input[j]);
                }
            }
        }
        System.out.println("Candy(): TEST PASSED");
    }

    @Test
    void test_Complete_Chips_Purchase_Items_Remaining() {
        Chips chips = new Chips();
        String[] input = {"Potato Crisps", "Stackers", "Grain Waves", "Cloud Popcorn", "\n", "Donut", "dhg345", "-1"};

        for ( int i = 4; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                int remaining = chips.completeChipsPurchase(input[j]);
                if (remaining != 8951423) {
                    Assert.assertEquals("Test input:<" + input[j] + "> ", i, remaining);
//                System.out.println("Remaining: " + i  + " Expected: " + remaining + " | " + input[j]);
                }
            }
        }
        System.out.println("Chips(): TEST PASSED");
    }

    @Test
    void test_Complete_Drinks_Purchase_Items_Remaining() {
        Drinks drinks = new Drinks();
        String[] input = {"Cola", "Dr. Salt", "Mountain Melter", "Heavy", "\n", "Donut", "dhg345", "-1"};

        for ( int i = 4; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                int remaining = drinks.completeDrinksPurchase(input[j]);
                if (remaining != 8951423) {
                    Assert.assertEquals("Test input:<" + input[j] + "> ", i, remaining);
//                System.out.println("Remaining: " + i  + " Expected: " + remaining + " | " + input[j]);
                }
            }
        }
        System.out.println("Drinks(): TEST PASSED");
    }

    @Test
    void test_Complete_Gum_Purchase_Items_Remaining() {
        Gum gum = new Gum();
        String[] input = {"U-Chews", "Little League Chew", "Chiclets", "Triplemint", "\n", "Donut", "dhg345", "-1"};

        for ( int i = 4; i >= 0; i--) {
            for (int j = 7; j >= 0; j--) {
                int remaining = gum.completeGumPurchase(input[j]);
                if (remaining != 8951423) {
                    Assert.assertEquals("Test input:<" + input[j] + "> ", i, remaining);
//                System.out.println("Remaining: " + i  + " Expected: " + remaining + " | " + input[j]);
                }
            }
        }
        System.out.println("Gum(): TEST PASSED");
    }

}