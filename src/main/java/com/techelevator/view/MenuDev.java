package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class MenuDev {

    private PrintWriter out;
    private Scanner in;

    public MenuDev(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public String firstChoice(){
        out.println("(1) Display Vending Machine Items \n(2) Purchase");
        out.flush();
        String firstChoice = in.nextLine();
        return firstChoice;
    }

    public String secondChoice(double balance){
        out.println("(1) Feed Money \n(2) Select Product \n(3) Finish Transaction \nCurrent Money: $" + balance);
        out.flush();
        String choice = in.nextLine();
        switch (choice){
            case "1":
                return "1";
            case "2":
                return "2";
            case "3":
                return "3";
        }
        return "4";
    }

    public String getItemChoice() {
        out.println("Please input an Item code");
        out.flush();
        return in.nextLine().toUpperCase();
    }

    public double getMoney() {
        out.println("How much money would you like to add? \n (1) $1 \n (2) $2 \n (3) $5 \n (4) $10");
        out.flush();
        String choice = in.nextLine();
        switch (choice) {

            case "1" :
                return 1.00;

            case "2":
                return 2.00;

            case "3":
                return 5.00;

            case "4":
                return 10.00;

            default:
                out.println("Please enter a valid amount of money");
                out.flush();
                break;
        }
        return 0;
    }


    public void printMsgToUser(String msg){
        out.print(msg);
        out.flush();
    }
}
