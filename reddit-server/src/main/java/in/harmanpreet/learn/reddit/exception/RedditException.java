package in.harmanpreet.learn.reddit.exception;

import org.springframework.mail.MailException;

public class RedditException extends RuntimeException {
    public RedditException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public RedditException(String exMessage) {
        super(exMessage);
    }
}
