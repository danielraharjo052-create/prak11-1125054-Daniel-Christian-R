package model;

import java.util.List;
import authorization.AuthorizationStrategy;

public interface PaymentStrategy {

    boolean validate(double amount);

    void pay(double amount);

    List<AuthorizationMethod> getRequiredAuthorizations();

    boolean canPay(double amount);

    AuthorizationStrategy getAuthorizationStrategy(AuthorizationMethod method);
}