package com.techelevator;

/** Gum.java - Pushed from Backup */
public class Gum extends Snack {
    private static int uChewsLeft = 5;
    private static int littleLeagueChewLeft = 5;
    private static int chicletsLeft = 5;
    private static int triplemintLeft = 5;

    private double uChewsPrice = 0.85;
    private double littleLeagueChewPrice = 0.95;
    private double chicletsPrice = 0.75;
    private double tripleMintPrice = 0.75;

    /** METHODS: Decrement product quantity */
    public int completeGumPurchase(String itemSelected) {
        if (itemSelected.equals("U-Chews") || itemSelected.equals("Little League Chew") ||
            itemSelected.equals("Chiclets") || itemSelected.equals("Triplemint")) {
            switch (itemSelected) {
                case "U-Chews":
                    uChewsLeft--;
                    return uChewsLeft;
                case "Little League Chew":
                    littleLeagueChewLeft--;
                    return littleLeagueChewLeft;
                case "Chiclets":
                    chicletsLeft--;
                    return chicletsLeft;
                case "Triplemint":
                    triplemintLeft--;
                    return triplemintLeft;
                default:
                    break;
            }
        } else return 8951423;
        return 0;
    }

    /** Remaining: GETTERS & SETTERS */
    public int getuChewsLeft() {
        return uChewsLeft;
    }
    public void setuChewsLeft(int uChewsLeft) {
        Gum.uChewsLeft = uChewsLeft;
    }

    public int getLittleLeaugeChewLeft() {
        return littleLeagueChewLeft;
    }
    public void setLittleLeaugeChewLeft(int littleLeaugeChewLeft) {
        littleLeagueChewLeft = littleLeaugeChewLeft;
    }

    public int getChicletsLeft() {
        return chicletsLeft;
    }
    public void setChicletsLeft(int chicletsLeft) {
        Gum.chicletsLeft = chicletsLeft;
    }

    public int getTriplemintLeft() {
        return triplemintLeft;
    }
    public void setTriplemintLeft(int triplemintLeft) {
        Gum.triplemintLeft = triplemintLeft;
    }

    /** Price: GETTERS & SETTERS */
    public double getUChewsPrice() {
        return uChewsPrice;
    }
    public void setuChewsPrice(double uChewsPrice) {
        this.uChewsPrice = uChewsPrice;
    }

    public double getLittleLeaugeChewPrice() {
        return littleLeagueChewPrice;
    }
    public void setLittleLeagueChewPrice(double littleLeagueChewPrice) {
        this.littleLeagueChewPrice = littleLeagueChewPrice;
    }

    public double getChicletsPrice() {
        return chicletsPrice;
    }
    public void setChicletsPrice(double chicletsPrice) {
        this.chicletsPrice = chicletsPrice;
    }

    public double getTriplemintPrice() {
        return tripleMintPrice;
    }
    public void setTripleMintPrice(double tripleMintPrice) {
        this.tripleMintPrice = tripleMintPrice;
    }

}
