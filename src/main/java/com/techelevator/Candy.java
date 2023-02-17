package com.techelevator;

/** Candy.java - Pushed from Backup */
public class Candy extends Snack {
    private static int moonPiesLeft = 5;
    private static int cowtalesLeft = 5;
    private static int wonkaBarsLeft = 5;
    private static int crunchiesLeft = 5;

    private double moonPiePrice = 1.80;
    private double cowtalesPrice = 1.50;
    private double wonkaBarsPrice = 1.50;
    private double crunchiesPrice = 1.75;

    /** METHODS: Decrement product quantity */
    public int completeCandyPurchase(String itemSelected) {
        if (itemSelected.equals("Moonpie") || itemSelected.equals("Cowtales") ||
            itemSelected.equals("Wonka Bar") || itemSelected.equals("Crunchie")) {
            switch (itemSelected) {
                case "Moonpie":
                    moonPiesLeft--;
                    return moonPiesLeft;
                case "Cowtales":
                    cowtalesLeft--;
                    return cowtalesLeft;
                case "Wonka Bar":
                    wonkaBarsLeft--;
                    return wonkaBarsLeft;
                case "Crunchie":
                    crunchiesLeft--;
                    return crunchiesLeft;
                default:
                    break;
            }
        } else return 8951423;
        return 0;
    }

    /** Remaining: GETTERS & SETTERS */
    public int getMoonpiesLeft() {
        return moonPiesLeft;
    }
    public void setMoonPiesLeft(int moonPiesLeft) {
        Candy.moonPiesLeft = moonPiesLeft;
    }

    public void setCowtalesLeft(int cowtalesLeft) {
        Candy.cowtalesLeft = cowtalesLeft;
    }
    public int getCowtalesLeft() {
        return cowtalesLeft;
    }

    public int getWonkaBarsLeft() {
        return wonkaBarsLeft;
    }
    public void setWonkaBarsLeft(int wonkaBarsLeft) {
        Candy.wonkaBarsLeft = wonkaBarsLeft;
    }

    public int getCrunchiesLeft() {
        return crunchiesLeft;
    }
    public void setCrunchiesLeft(int crunchiesLeft) {
        Candy.crunchiesLeft = crunchiesLeft;
    }

    /** Price: GETTERS & SETTERS */
    public double getMoonPiePrice() {
        return moonPiePrice;
    }
    public void setMoonPiePrice(double moonPiePrice) {
        this.moonPiePrice = moonPiePrice;
    }

    public double getCowtalesPrice() {
        return cowtalesPrice;
    }
    public void setCowtalesPrice(double cowtalesPrice) {
        this.cowtalesPrice = cowtalesPrice;
    }

    public double getWonkaBarsPrice() {
        return wonkaBarsPrice;
    }
    public void setWonkaBarsPrice(double wonkaBarsPrice) {
        this.wonkaBarsPrice = wonkaBarsPrice;
    }

    public void setCrunchiesPrice(double crunchiesPrice) {
        this.crunchiesPrice = crunchiesPrice;
    }
    public double getCrunchiesPrice() {
        return crunchiesPrice;
    }

}
