package com.rekrutacja.zadanie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rekrutacja.zadanie.controllers.UserRESTController;
import com.rekrutacja.zadanie.model.DTO.ContactInfoDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTOWithoutContactInfos;
import com.rekrutacja.zadanie.services.interfaces.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserRESTControllerTest {

    private UserRESTController userRestController;
    @Mock
    private UserService userService;

    private UserDTO userDto;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userRestController = new UserRESTController(userService, new ObjectMapper(), null);
        userDto = new UserDTO();
        userDto.setFirstName("Test");
        userDto.setLastName("Test");
        userDto.setPesel("12345678912");
        userDto.setContactInfos(new HashSet<>());
    }

    @Test
    public void testCreateUser() throws Exception {
        when(userService.createUser(any(UserDTOWithoutContactInfos.class))).thenReturn(userDto);
        ResponseEntity<UserDTO> response = userRestController.createUser(new UserDTOWithoutContactInfos());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }

    @Test
    public void testAddContactInfo() throws Exception {
        String pesel = "12345678912";
        ContactInfoDTO contactInfoDto = new ContactInfoDTO();
        contactInfoDto.setEmail("test@example.com");
        /*contactInfoDto.setResidenceAddress("Residence Address");
        contactInfoDto.setRegistrationAddress("Registration Address");
        contactInfoDto.setPrivatePhoneNumber("+123456789");
        contactInfoDto.setWorkPhoneNumber("+987654321");*/

        when(userService.addContactInfoByPesel(pesel, contactInfoDto)).thenReturn(userDto);
        ResponseEntity<UserDTO> response = userRestController.addContactInfo(pesel, contactInfoDto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userDto, response.getBody());
    }
}