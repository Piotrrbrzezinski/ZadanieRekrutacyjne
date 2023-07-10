package com.rekrutacja.zadanie.services.interfaces;

import com.rekrutacja.zadanie.model.DTO.ContactInfoDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTOWithoutContactInfos;
import com.rekrutacja.zadanie.model.entitys.User;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTOWithoutContactInfos user);
    UserDTO addContactInfoByPesel(String pesel, @Valid ContactInfoDTO contactInfo);

    @Transactional
    UserDTO addContactInfoByUserId(Long userId, ContactInfoDTO contactInfoDTO);

    List<UserDTO> getAllUsers();
    UserDTO getUserByPeseltoDTO(String pesel);

    void exportUsersToFile();

    User addUser(UserDTO newUser);
}
