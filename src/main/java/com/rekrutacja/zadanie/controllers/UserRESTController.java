package com.rekrutacja.zadanie.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rekrutacja.zadanie.exceptions.UserNotFoundException;
import com.rekrutacja.zadanie.model.DTO.ContactInfoDTO;


import com.rekrutacja.zadanie.model.DTO.UserDTO;

import com.rekrutacja.zadanie.model.DTO.UserDTOWithoutContactInfos;
import com.rekrutacja.zadanie.services.interfaces.UserGenerationService;
import com.rekrutacja.zadanie.services.interfaces.UserService;
import io.swagger.annotations.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserRESTController {

    private final UserService userService;
    private ObjectMapper objectMapper;
    private UserGenerationService userGenerationService;

    @ApiOperation(value = "Metoda:1 Dodanie użytkownika do systemu (podstawowe dane: imie, nazwisko, pesel)", response = UserDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully created User"),
            @ApiResponse(code = 400, message = "Invalid UserDTO supplied"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTOWithoutContactInfos userDto) {
        UserDTO savedUser = userService.createUser(userDto);
        UserDTO userDTO = objectMapper.convertValue(savedUser, UserDTO.class);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }


    @ApiOperation(value = "Metoda:2 Dodanie dla użytkownika metod komunikacji (słownik adresów: email, adres zamieszkania, zameldowania, numer telefonu prywatny, służbowy).")
    @PostMapping("/{pesel}/contactInfos")
    public ResponseEntity<UserDTO> addContactInfo(@PathVariable String pesel, @Valid @RequestBody ContactInfoDTO contactInfoDto) {

        UserDTO userDTO =  userService.addContactInfoByPesel(pesel, contactInfoDto);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Metoda:3 pobiera wszystkich użytkowników z systemu.")

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Metoda 4: pobiera danego użytkownika po pesel.")
    @GetMapping("/{pesel}")
    public UserDTO getUserByPesel(@PathVariable String pesel) {
        return userService.getUserByPeseltoDTO(pesel);
    }

    @ApiOperation(value = "UC:4 RESTOWA metoda która zapisze wszystkich użytkowników do pliku.")
    @PostMapping("/export")
    public ResponseEntity<?> exportUsersToFile() {
        try {
            userService.exportUsersToFile();
            return new ResponseEntity<>("Users have been successfully written to file", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error occurred while writing users to file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Automayczne wygenerowanie 15.000 użytkowników + 2-4 metod komunikacji (pseudolosowa dla każdego użytkownika).")
    @PostMapping("/generation")
    public ResponseEntity<?> generationUsers() {
        try {
            userGenerationService.generateUsersAndContactInfos();
            return new ResponseEntity<>("Users have been successfully written to file", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Error occurred while writing users to file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
