package in.harmanpreet.learn.reddit.util;

import in.harmanpreet.learn.reddit.enums.MailTemplate;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.persistence.ManyToOne;

@Service
@AllArgsConstructor
public class MailContentBuilder {
    private final TemplateEngine templateEngine;

    public String build(MailTemplate mailTemplate, String message) {
        Context context = new Context();
        context.setVariable("message", message);
        return templateEngine.process(mailTemplate.getTemplateName(), context);
    }
}
