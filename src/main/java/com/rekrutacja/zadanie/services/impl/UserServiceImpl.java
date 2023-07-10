package com.rekrutacja.zadanie.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rekrutacja.zadanie.exceptions.UserNotFoundException;
import com.rekrutacja.zadanie.model.DTO.ContactInfoDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTOWithoutContactInfos;
import com.rekrutacja.zadanie.model.entitys.ContactInfo;
import com.rekrutacja.zadanie.model.entitys.User;
import com.rekrutacja.zadanie.repository.UserRepository;
import com.rekrutacja.zadanie.services.interfaces.ContactInfoService;
import com.rekrutacja.zadanie.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private ContactInfoService contactInfoService;

    private ObjectMapper objectMapper;

    @Override
    public UserDTO createUser(UserDTOWithoutContactInfos userDTO) {
        var user = objectMapper.convertValue(userDTO, User.class);
        userRepository.save(user);
        return objectMapper.convertValue(user, UserDTO.class);
    }

    @Override
    @Transactional
    public User addUser(UserDTO userDTO) {
        var user = objectMapper.convertValue(userDTO, User.class);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserDTO addContactInfoByPesel(String pesel, ContactInfoDTO contactInfoDTO) {
        User user = getUserByPesel(pesel);
        return addContactInfoToUser(user, contactInfoDTO);
    }

    @Override
    @Transactional
    public UserDTO addContactInfoByUserId(Long userId, ContactInfoDTO contactInfoDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return addContactInfoToUser(user, contactInfoDTO);
    }

    private UserDTO addContactInfoToUser(User user, ContactInfoDTO contactInfoDTO) {
        ContactInfo contactInfo = objectMapper.convertValue(contactInfoDTO, ContactInfo.class);
        contactInfo.setUser(user);
        contactInfoService.createContactInfo(contactInfo);
        return objectMapper.convertValue(user, UserDTO.class);
    }


    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(this::mapUserToUserDTO)
                .collect(Collectors.toList());
    }

    private UserDTO mapUserToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPesel(user.getPesel());

        // Tworzymy nowy zestaw ContactInfoDTO na podstawie ContactInfo
        Set<ContactInfoDTO> contactInfoDTOs = user.getContactInfos()
                .stream()
                .map(this::mapContactInfoToContactInfoDTO)
                .collect(Collectors.toSet());

        userDTO.setContactInfos(contactInfoDTOs);

        return userDTO;
    }

    private ContactInfoDTO mapContactInfoToContactInfoDTO(ContactInfo contactInfo) {
        ContactInfoDTO contactInfoDTO = new ContactInfoDTO();

        contactInfoDTO.setEmail(contactInfo.getEmail());
        contactInfoDTO.setResidenceAddress(contactInfo.getResidenceAddress());
        contactInfoDTO.setRegistrationAddress(contactInfo.getRegistrationAddress());
        contactInfoDTO.setPrivatePhoneNumber(contactInfo.getPrivatePhoneNumber());
        contactInfoDTO.setWorkPhoneNumber(contactInfo.getWorkPhoneNumber());

        return contactInfoDTO;
    }


    @Override
    public UserDTO getUserByPeseltoDTO(String pesel) {
        var user = getUserByPesel(pesel);
        return mapUserToUserDTO(user);
    }


    public User getUserByPesel(String pesel) {
        return userRepository.findByPesel(pesel).orElseThrow(() -> new UserNotFoundException("User not found with pesel: " + pesel));
    }

    public void exportUsersToFile() {
        List<User> users = userRepository.findAll();

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("user.json"), users);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }

}