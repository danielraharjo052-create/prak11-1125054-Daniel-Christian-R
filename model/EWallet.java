package model;

import java.util.List;
import authorization.AuthorizationStrategy;
import authorization.PinAuthorization;
import authorization.OtpAuthorization;

public class EWallet implements PaymentStrategy {

    private String phoneNumber;
    private double balance;
    private String pin;

    public EWallet(String phoneNumber, double balance, String pin) {
        this.phoneNumber = phoneNumber;
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

        System.out.println("Pembayaran E-Wallet berhasil!");
        System.out.println("Sisa saldo: " + balance);
    }

    @Override
    public List<AuthorizationMethod> getRequiredAuthorizations() {
        return List.of(
                AuthorizationMethod.PIN,
                AuthorizationMethod.OTP
        );
    }

    @Override
    public boolean canPay(double amount) {
        return amount > 0;
    }

    @Override
    public AuthorizationStrategy getAuthorizationStrategy(
            AuthorizationMethod method) {

        if (method == AuthorizationMethod.PIN) {
            return new PinAuthorization(pin);
        } else if (method == AuthorizationMethod.OTP) {
            return new OtpAuthorization("123456");
        }

        return null;
    }

    @Override
    public String toString() {
        return "E-Wallet - " + phoneNumber + " (Saldo: " + balance + ")";
    }
}