package authorization;

public class PinAuthorization implements AuthorizationStrategy {

    private String pin;

    public PinAuthorization(String pin) {
        this.pin = pin;
    }

    @Override
    public boolean validate(String input) {
        return pin.equals(input);
    }
}