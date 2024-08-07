package com.vansisto.cookandlaughbe.service;

import com.vansisto.cookandlaughbe.entity.User;
import jakarta.mail.MessagingException;

public interface MailService {
    void sendValidationEmail(User user) throws MessagingException;
}
