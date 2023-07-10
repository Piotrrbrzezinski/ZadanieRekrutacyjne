package com.rekrutacja.zadanie.model.entitys;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;


@Data
@Entity
public class ContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Email
    private String email;

    private String residenceAddress;


    private String registrationAddress;


    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    private String privatePhoneNumber;


    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
    private String workPhoneNumber;

    @JsonBackReference
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

}

