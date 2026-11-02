package model;

import java.util.List;
import java.util.ArrayList;
import authorization.AuthorizationStrategy;
import authorization.PinAuthorization;
import authorization.CcvAuthorization;

public class CreditCard implements PaymentStrategy {

    private String cardNumber;
    private double limit;
    private double currentDebt;
    private String ccv;
    private String pin;

    public CreditCard(String cardNumber, double limit,
                      String ccv, String pin) {

        this.cardNumber = cardNumber;
        this.limit = limit;
        this.currentDebt = 0;
        this.ccv = ccv;
        this.pin = pin;
    }

    @Override
    public boolean validate(double amount) {
        return amount > 0 && (currentDebt + amount) <= limit;
    }

    @Override
    public void pay(double amount) {
        currentDebt += amount;

        System.out.println("Pembayaran Credit Card berhasil!");
        System.out.println("Total hutang: " + currentDebt);
        System.out.println("Sisa limit: " + (limit - currentDebt));
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of(
                AuthorizationMethod.CCV,
                AuthorizationMethod.PIN
        );
    }

    @Override
    public boolean canPay(double amount) {
        return amount >= 100000 && amount <= 100000000;
    }

    @Override
    public AuthorizationStrategy getAuthorizationStrategy(
            AuthorizationMethod method) {

        if (method == AuthorizationMethod.PIN) {
            return new PinAuthorization(pin);
        } else if (method == AuthorizationMethod.CCV) {
            return new CcvAuthorization(ccv);
        }

        return null;
    }

    @Override
    public String toString() {
        return "Credit Card - " + cardNumber
                + " (Sisa Limit: " + (limit - currentDebt) + ")";
    }
}