package model;

import java.util.ArrayList;
import java.util.List;
import authorization.AuthorizationStrategy;
import authorization.PinAuthorization;

public class DebitCard implements PaymentStrategy {

    private String cardNumber;
    private double balance;
    private String pin;

    public DebitCard(String cardNumber, double balance, String pin) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.pin = pin;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && balance >= amount;
    }

    @Override
    public void pay(double amount) {
        balance -= amount;

        System.out.println("Pembayaran Debit berhasil!");
        System.out.println("Sisa saldo: " + balance);
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of(AuthorizationMethod.PIN);
    }

    @Override
    public boolean canPay(double amount) {
        return amount > 0 && amount <= 10000000;
    }

    @Override
    public AuthorizationStrategy getAuthorizationStrategy(
            AuthorizationMethod method) {

        if (method == AuthorizationMethod.PIN) {
            return new PinAuthorization(pin);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Debit Card - " + cardNumber + " (Saldo: " + balance + ")";
    }
}