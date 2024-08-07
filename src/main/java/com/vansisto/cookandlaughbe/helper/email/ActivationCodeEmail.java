package com.vansisto.cookandlaughbe.helper.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ActivationCodeEmail {
    private String to;
    private String username;
    private String subject;
    private EmailTemplate emailTemplate;
    private String confirmationUrl;
    private String activationCode;
}
