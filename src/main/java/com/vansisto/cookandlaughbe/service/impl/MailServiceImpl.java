package com.vansisto.cookandlaughbe.service.impl;

import com.google.common.base.Strings;
import com.vansisto.cookandlaughbe.config.properties.app.registration.ActivationCodePropertyConfig;
import com.vansisto.cookandlaughbe.entity.User;
import com.vansisto.cookandlaughbe.helper.email.ActivationCodeEmail;
import com.vansisto.cookandlaughbe.helper.email.EmailTemplate;
import com.vansisto.cookandlaughbe.service.ActivationCodeService;
import com.vansisto.cookandlaughbe.service.AsyncMailService;
import com.vansisto.cookandlaughbe.service.MailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailServiceImpl implements MailService {

    public static final String ACCOUNT_ACTIVATION_SUBJECT = "Account activation";

    private final ActivationCodeService activationCodeService;
    private final ActivationCodePropertyConfig activationCodePropertyConfig;
    private final AsyncMailService asyncMailService;

    @Override
    public void sendValidationEmail(User user) throws MessagingException {
        String activationCode = activationCodeService.generateAndSaveActivationCode(user);
        asyncMailService.sendEmail(new ActivationCodeEmail()
                .setTo(user.getEmail())
                .setUsername(defineUsername(user))
                .setEmailTemplate(EmailTemplate.ACTIVATE_ACCOUNT)
                .setConfirmationUrl(activationCodePropertyConfig.getConfirmationUrl())
                .setActivationCode(activationCode)
                .setSubject(ACCOUNT_ACTIVATION_SUBJECT)
        );
    }

    private String defineUsername(User user) {
        if (Strings.isNullOrEmpty(user.getFirstname())) {
            return user.getEmail();
        }

        String lastname = Strings.isNullOrEmpty(user.getLastname()) ? "" : " " + user.getLastname();
        return user.getFirstname() + lastname;
    }
}
