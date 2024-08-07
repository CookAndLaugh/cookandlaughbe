package com.vansisto.cookandlaughbe.service;

import com.vansisto.cookandlaughbe.entity.User;

public interface ActivationCodeService {
    String generateAndSaveActivationCode(User user);
}
