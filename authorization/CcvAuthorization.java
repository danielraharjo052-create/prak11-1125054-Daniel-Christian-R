package authorization;

public class CcvAuthorization implements AuthorizationStrategy {

    private String ccv;

    public CcvAuthorization(String ccv) {
        this.ccv = ccv;
    }

    @Override
    public boolean validate(String input) {
        return ccv.equals(input);
    }
}