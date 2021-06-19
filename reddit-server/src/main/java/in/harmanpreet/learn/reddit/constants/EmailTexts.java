package in.harmanpreet.learn.reddit.constants;

public class EmailTexts {

    public static final String ACCOUNT_VERIFICATION_URL = "http://localhost:8080/api/auth/activate/";
    public static final String ACTIVATION_EMAIL_BODY = new StringBuilder("Thank you for signing up to Reddit,")
            .append("please click on the below url to activate your account:")
            .append(ACCOUNT_VERIFICATION_URL)
            .toString();
}
