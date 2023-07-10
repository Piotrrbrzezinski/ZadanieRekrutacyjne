package com.rekrutacja.zadanie.model.DTO;

import com.rekrutacja.zadanie.model.entitys.User;
import lombok.Data;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
public class ContactInfoDTO {

    @Email
    private String email;

    //@NotEmpty
    private String residenceAddress;

    //@NotEmpty
    private String registrationAddress;

    //@NotEmpty
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    private String privatePhoneNumber;

    //@NotEmpty
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    private String workPhoneNumber;

}
