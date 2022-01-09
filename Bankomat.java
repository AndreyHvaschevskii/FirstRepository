package com.company;


import com.company.Banks.Banks;
import com.company.Cards.CreditCard;
import com.company.Cards.Kartochka;

import java.util.Scanner;

public class Bankomat extends Kartochka {
    Kartochka k = new Kartochka();
    CreditCard cr = new CreditCard();

    Banks bank = Banks.ALPHA_BANK;
    boolean error = false;
    boolean provBank = false;
    boolean block = false;

    public void enterCard() {
        Scanner ins = new Scanner(System.in);
        System.out.println("Insert your card");
        System.out.println("Confirm that you have inserted the card by pressing Yes");
        String i = ins.nextLine();
        if (i.equalsIgnoreCase("yes")) {
            bank();
        } else {
            enterCard();
        }
    }

    private void bank() {
        if (bank == k.b) {
            provBank = true;
            if (block == true) {
                block();
            } else {
                chekPin();
            }
        } else {
            System.out.println("This ATM does not service your bank's cards");
            System.out.println("Take the card");
        }
    }

    private void chekPin() {
        Scanner sc = new Scanner(System.in);
        int c = 3;
        do {
            System.out.println("Enter your pin");
            String n = sc.nextLine();
            if (n.equals(k.getPin())) {
                System.out.println("Pin confirmed");
                choice();
            } else if (c != 1) {
                --c;
                System.out.println("The password is not correct, " + c + " attempts left");
                error = true;
            } else {
                System.out.println("Your card is blocked. Please contact the bank");
                System.out.println("Take the card");
                block = true;
                enterCard();
            }
        } while (error);
    }

    private void choice() {
        Scanner sc = new Scanner(System.in);
        String a = "1";
        String b = "2";
        String c = "3";
        System.out.println("Select an action: ");
        System.out.println("Enter 1 to withdraw cash");
        System.out.println("To view the balance enter 2");
        System.out.println("To return the card, click 3");
        String n = sc.nextLine();
        if (n.equals(a)) {
            giveMoney();
        } else if (n.equals(b)) {
            chekOstatok();
        } else if (n.equals(c)) {
            System.out.println("Take the card");
        } else {
            System.out.println("Ne vervoe znachenie. Enter correct number");
            choice();
        }
    }

    private void chekOstatok() {
        if (k.ostatok > 0) {
            System.out.println("Remaining on your card " + k.ostatok + " usd");
            back();
        } else {
            System.out.println("Remaining on your card " + k.ostatok + " usd");
            System.out.println("Loan debt: " + cr.crOstatok);
            back();
        }
    }

    private void giveMoney() {
        if (k.ostatok == 0) {
            System.out.println("Your balance is 0");
            credit();
        } else {
            System.out.println("To withdraw money, enter the amount. If the sum is not an integer, separate the fractional part with a comma.");
            Scanner sc = new Scanner(System.in);
            double sum = sc.nextDouble();
            if (k.ostatok != 0 && sum > 0 && sum <= k.ostatok) {
                k.ostatok -= sum;
                System.out.println("Take the money");
                System.out.println("Remaining on your card " + k.ostatok + " usd");
                back();
            } else if (sum == 0 || sum < 0) {
                System.out.println("Invalid value");
                back();
            } else {
                credit();
            }
        }
    }

    private void back() {
        String d = "4";
        Scanner sc = new Scanner(System.in);
        System.out.println("To return back press 4");
        String l = sc.nextLine();
        if (l.equals(d)) {
            choice();
        } else {
            System.out.println("Ne vervoe znachenie. Enter correct number");
            back();
        }
    }

    private void credit() {
        System.out.println("To withdraw an amount exceeding the balance on the card, you must confirm the loan.");
        System.out.println("To confirm the loan, enter Yes.");
        Scanner sc = new Scanner(System.in);
        String a = sc.nextLine();
        if (a.equalsIgnoreCase("yes")) {
            System.out.println("Loan approved");
            giveCreditMoney();
        } else {
            System.out.println("Loan declined");
            back();
        }
    }

    private void giveCreditMoney() {
        System.out.println("Enter the loan amount. If the sum is not an integer, separate the fractional part with a comma.");
        Scanner sc = new Scanner(System.in);
        double sum = sc.nextDouble();
        if (sum > 0 && sum > k.ostatok) {
            if (k.ostatok > 0) {
                cr.crOstatok += (k.ostatok - sum) * (-1);
                k.ostatok = 0;
            } else {
                cr.crOstatok += sum;
            }
            System.out.println("The loan amount was: " + cr.crOstatok + " usd");
            System.out.println("Take the money");
            back();
        } else {
            System.out.println("Ne vernaia summa. Enter correct summ.");
            back();
        }
    }

    private void block() {
        System.out.println("Your card is blocked. Please contact the bank");
        System.out.println("Take the card");
        enterCard();
    }
}


