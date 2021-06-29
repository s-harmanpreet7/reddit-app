package in.harmanpreet.learn.reddit.enums;

public enum MailTemplate {
    NOT_VALID(""),
    ACCOUNT_VERIFICATION("VerificationMailTemplate"),
    COMMENT_NOTIFICATION("CommentNotificationMailTemplate");

    private final String templateName;

    private MailTemplate(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateName() {
        return this.templateName;
    }
}
