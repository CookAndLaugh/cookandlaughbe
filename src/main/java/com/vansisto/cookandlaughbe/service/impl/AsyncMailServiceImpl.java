package com.vansisto.cookandlaughbe.service.impl;

import com.vansisto.cookandlaughbe.config.properties.app.domain.DomainPropertyConfig;
import com.vansisto.cookandlaughbe.helper.email.ActivationCodeEmail;
import com.vansisto.cookandlaughbe.helper.email.EmailTemplate;
import com.vansisto.cookandlaughbe.service.AsyncMailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;

@RequiredArgsConstructor
@Service
public class AsyncMailServiceImpl implements AsyncMailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    private final DomainPropertyConfig domainPropertyConfig;

    @Override
    @Async
    public void sendEmail(ActivationCodeEmail activationCodeEmail) throws MessagingException {
        Context context = new Context();
        context.setVariables(buildContextVariables(activationCodeEmail));

        MimeMessageHelper mimeMessageHelper = buildMimeMessageHelper(activationCodeEmail);
        String template = generateTemplate(activationCodeEmail, context);
        mimeMessageHelper.setText(template, true);

        mailSender.send(mimeMessageHelper.getMimeMessage());
    }

    private String generateTemplate(ActivationCodeEmail activationCodeEmail, Context context) {
        EmailTemplate emailTemplate = activationCodeEmail.getEmailTemplate();
        String templateName = Objects.isNull(emailTemplate) ? "confirm-email" : emailTemplate.getName();
        return templateEngine.process(templateName, context);
    }

    private Map<String, Object> buildContextVariables(ActivationCodeEmail activationCodeEmail) {
        return Map.of(
                "username", activationCodeEmail.getUsername(),
                "confirmationUrl", activationCodeEmail.getConfirmationUrl(),
                "activationCode", activationCodeEmail.getActivationCode()
        );
    }

    private MimeMessageHelper buildMimeMessageHelper(ActivationCodeEmail activationCodeEmail) throws MessagingException {
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), MULTIPART_MODE_MIXED, UTF_8.name());
        mimeMessageHelper.setFrom("confirm@%s".formatted(domainPropertyConfig.getRoot()));
        mimeMessageHelper.setTo(activationCodeEmail.getTo());
        mimeMessageHelper.setSubject(activationCodeEmail.getSubject());
        return mimeMessageHelper;
    }

}
