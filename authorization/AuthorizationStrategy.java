package authorization;

public interface AuthorizationStrategy {

    boolean validate(String input);
}