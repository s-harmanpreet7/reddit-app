package in.harmanpreet.learn.reddit.service;

import in.harmanpreet.learn.reddit.enums.MailTemplate;
import in.harmanpreet.learn.reddit.exception.RedditException;
import in.harmanpreet.learn.reddit.model.NotificationEmail;
import in.harmanpreet.learn.reddit.util.MailContentBuilder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender mailSender;
    private final MailContentBuilder verificationMailContentBuilder;

    @Async
    void sendMail(MailTemplate mailTemplate, NotificationEmail notificationEmail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("support@devteam.com");
            messageHelper.setTo(notificationEmail.getRecipient());
            messageHelper.setSubject(notificationEmail.getSubject());
            messageHelper.setText(verificationMailContentBuilder.build(mailTemplate, notificationEmail.getBody()));
        };

        try {
            mailSender.send(messagePreparator);
            log.info("Verification email sent!");
        } catch (MailException e) {
            throw new RedditException("Exception encountered while sending verification email to " + notificationEmail.getRecipient(), e);
        }
    }
}
