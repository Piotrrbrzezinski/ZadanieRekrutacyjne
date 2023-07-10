package com.rekrutacja.zadanie.model.DTO;

import com.rekrutacja.zadanie.model.entitys.ContactInfo;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
public class UserDTO {


    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    @Size(min = 11, max = 11)
    private String pesel;

    private Set<ContactInfoDTO> contactInfos;


}
