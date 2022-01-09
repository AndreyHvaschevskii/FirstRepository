package com.company.Cards;

import com.company.Banks.Banks;

public class Kartochka {
    public Banks b = Banks.ALPHA_BANK;
    private String pin = "9876";
    private String[] number;
    private String namePerson;
    public double ostatok = 500;

    public String getPin() {
        return pin;
    }
}
