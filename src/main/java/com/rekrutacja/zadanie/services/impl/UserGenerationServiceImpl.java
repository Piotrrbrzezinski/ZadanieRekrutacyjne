package com.rekrutacja.zadanie.services.impl;

import com.rekrutacja.zadanie.model.DTO.ContactInfoDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTO;
import com.rekrutacja.zadanie.model.entitys.User;
import com.rekrutacja.zadanie.services.interfaces.UserGenerationService;
import com.rekrutacja.zadanie.services.interfaces.UserService;
import com.rekrutacja.zadanie.utility.RandomUserGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
@AllArgsConstructor
public class UserGenerationServiceImpl implements UserGenerationService {

    private static final int NUMBER_OF_USERS = 15000;

    private UserService userService;

    private RandomUserGenerator randomUserGenerator;

    @Override
    @Transactional
    public void generateUsersAndContactInfos() {
        Random random = new Random();

        for (int i = 0; i < NUMBER_OF_USERS; i++) {
            UserDTO newUser = randomUserGenerator.generateRandomUser();
            User savedUser = userService.addUser(newUser);


                ContactInfoDTO newContactInfo = randomUserGenerator.generateRandomContactInfo();
                userService.addContactInfoByUserId(savedUser.getId(), newContactInfo);

        }
    }
}
