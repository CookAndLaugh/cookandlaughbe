package com.vansisto.cookandlaughbe.service;

import com.vansisto.cookandlaughbe.helper.email.ActivationCodeEmail;
import jakarta.mail.MessagingException;

public interface AsyncMailService {
    void sendEmail(ActivationCodeEmail activationCodeEmail) throws MessagingException;
}
