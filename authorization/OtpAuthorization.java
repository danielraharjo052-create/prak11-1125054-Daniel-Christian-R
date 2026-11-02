package authorization;

public class OtpAuthorization implements AuthorizationStrategy {

    private String otp;

    public OtpAuthorization(String otp) {
        this.otp = otp;
    }

    @Override
    public boolean validate(String input) {
        return otp.equals(input);
    }
}