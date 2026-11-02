package processor;

import authorization.AuthorizationStrategy;
import java.util.List;
import java.util.Scanner;
import model.AuthorizationMethod;
import model.PaymentStrategy;

public class PaymentProcessor {

    private Scanner scanner;

    public PaymentProcessor(Scanner scanner) {
        this.scanner = scanner;
    }

    public void processPayment(PaymentStrategy strategy, double amount) {

        if (!strategy.canPay(amount)) {
            throw new IllegalArgumentException(
                    "Nominal pembayaran tidak sesuai aturan!"
            );
        }

        if (!strategy.validate(amount)) {
            throw new IllegalArgumentException(
                    "Saldo tidak mencukupi atau limit tidak cukup!"
            );
        }

        List<AuthorizationMethod> authorizations =
                strategy.getRequiredAuthorizations();

        for (int i = 0; i < authorizations.size(); i++) {

            AuthorizationMethod method = authorizations.get(i);

            System.out.print("Masukkan " + method + ": ");
            String input = scanner.nextLine();

            AuthorizationStrategy authorization =
                    strategy.getAuthorizationStrategy(method);

            if (authorization == null ||
                    !authorization.validate(input)) {

                throw new IllegalArgumentException(
                        "Otorisasi " + method + " gagal!"
                );
            }
        }

        strategy.pay(amount);
    }
}