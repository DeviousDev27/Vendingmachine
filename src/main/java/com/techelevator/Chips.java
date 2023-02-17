package com.techelevator;

/** Chips.java - Pushed from Backup */
public class Chips extends Snack {
    private static int potatoCrispsLeft = 5;
    private static int stackersLeft = 5;
    private static int grainWavesLeft = 5;
    private static int cloudPopcornLeft = 5;

    private double potatoCrispsPrice = 3.05;
    private double stackersPrice = 1.45;
    private double grainWavesPrice = 2.75;
    private double cloudPopcornPrice = 3.65;

    /** METHODS: Decrement product quantity */
    public int completeChipsPurchase(String itemSelected) {
        if (itemSelected.equals("Potato Crisps") || itemSelected.equals("Stackers") ||
            itemSelected.equals("Grain Waves") || itemSelected.equals("Cloud Popcorn")) {
            switch (itemSelected) {
                case "Potato Crisps":
                    potatoCrispsLeft--;
                    return potatoCrispsLeft;
                case "Stackers":
                    stackersLeft--;
                    return stackersLeft;
                case "Grain Waves":
                    grainWavesLeft--;
                    return grainWavesLeft;
                case "Cloud Popcorn":
                    cloudPopcornLeft--;
                    return cloudPopcornLeft;
                default:
                    break;
            }
        } else return 8951423;
        return 0;
    }

    /** Remaining: GETTERS & SETTERS */
    public int getPotatoCrispsLeft() {
        return potatoCrispsLeft;
    }
    public void setPotatoCrispsLeft(int potatoCrispsLeft) {
        Chips.potatoCrispsLeft = potatoCrispsLeft;
    }

    public int getStackersLeft() {
        return stackersLeft;
    }
    public void setStackersLeft(int stackersLeft) {
        Chips.stackersLeft = stackersLeft;
    }

    public int getGrainWavesLeft() {
        return grainWavesLeft;
    }
    public void setGrainWavesLeft(int grainWavesLeft) {
        Chips.grainWavesLeft = grainWavesLeft;
    }

    public int getCloudPopcornLeft() {
        return cloudPopcornLeft;
    }
    public void setCloudPopcornLeft(int cloudPopcornLeft) {
        Chips.cloudPopcornLeft = cloudPopcornLeft;
    }

    /** Price: GETTERS & SETTERS */
    public double getPotatoCrispsPrice() {
        return potatoCrispsPrice;
    }
    public void setPotatoCrispsPrice(double potatoCrispsPrice) {
        this.potatoCrispsPrice = potatoCrispsPrice;
    }

    public double getStackersPrice() {
        return stackersPrice;
    }
    public void setStackersPrice(double stackersPrice) {
        this.stackersPrice = stackersPrice;
    }

    public double getGrainWavesPrice() {
        return grainWavesPrice;
    }
    public void setGrainwavesPrice(double grainwavesPrice) {
        this.grainWavesPrice = grainwavesPrice;
    }

    public double getCloudPopcornPrice() {
        return cloudPopcornPrice;
    }
    public void setCloudPopcornPrice(double cloudPopcornPrice) {
        this.cloudPopcornPrice = cloudPopcornPrice;
    }

}
