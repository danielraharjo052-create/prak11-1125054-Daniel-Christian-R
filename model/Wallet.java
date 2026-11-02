package model;

import java.util.ArrayList;
import java.util.List;
import authorization.AuthorizationStrategy;

public class Wallet implements PaymentStrategy {

    private String name;
    private double cashValue;

    public Wallet(String name, double cashValue) {
        this.name = name;
        this.cashValue = cashValue;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && cashValue >= amount;
    }

    @Override
    public void pay(double amount) {
        cashValue -= amount;

        System.out.println("Pembayaran cash berhasil!");
        System.out.println("Sisa wallet: " + cashValue);
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return new ArrayList<>();
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 100 && amount <= 1000000;
    }

    @Override
    public AuthorizationStrategy getAuthorizationStrategy(
            AuthorizationMethod method) {
        return null;
    }

    @Override
    public String toString() {
        return "Cash - " + name + " (Saldo: " + cashValue + ")";
    }
}