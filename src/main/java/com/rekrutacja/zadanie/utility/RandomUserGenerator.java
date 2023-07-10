package com.rekrutacja.zadanie.utility;

import com.rekrutacja.zadanie.model.DTO.ContactInfoDTO;
import com.rekrutacja.zadanie.model.DTO.UserDTO;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomUserGenerator {

    private static final String[] FIRST_NAMES = {"Piotr", "Adam", "Jacek", "Anna", "Karolina", "Judyta"};
    private static final String[] LAST_NAMES = {"Jarecki", "Moszyński", "Kwiatkowski", "Marecki", "Nowak", "Wojtala"};

    private static final int PESEL_LENGTH = 11;

    private static final String[] cities = {"Warszawa", "Kraków", "Łódź", "Wrocław", "Poznań", "Stalowa Wola", "Katowice"};
    private static final String[] streets = {"ul. Polna", "ul. Słoneczna", "ul. Królewska", "ul. Ogrodowa", "ul. Leśna", "ul. Marszałkowska"};

    private static final String countryCode = "+48";
    private static final int phoneNumberLength = 9;
    private static final int maxBuildingNumber = 100;
    private static final Random RANDOM = new Random();

    private Random random = new Random();

    public UserDTO generateRandomUser() {
        String firstName = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)];
        String lastName = LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        String pesel = generateRandomPESEL();

        UserDTO user = new UserDTO();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPesel(pesel);

        return user;
    }

    public String generateRandomAddress() {
        String city = cities[random.nextInt(cities.length)];
        String street = streets[random.nextInt(streets.length)];
        int buildingNumber = random.nextInt(maxBuildingNumber) + 1;

        return street + " " + buildingNumber + ", " + city;
    }

    public ContactInfoDTO generateRandomContactInfo() {
        String email = generateRandomEmail();
        String residenceAddress = generateRandomAddress();
        String privatePhoneNumber = generateRandomPhoneNumber();

        ContactInfoDTO contactInfo = new ContactInfoDTO();
        contactInfo.setEmail(email);
        contactInfo.setRegistrationAddress(residenceAddress);
        contactInfo.setPrivatePhoneNumber(privatePhoneNumber);

        return contactInfo;
    }

    public String generateRandomPhoneNumber() {
        StringBuilder phoneNumber = new StringBuilder(countryCode);
        for (int i = 0; i < phoneNumberLength; i++) {
            int digit = random.nextInt(10);
            phoneNumber.append(digit);
        }
        return phoneNumber.toString();
    }

    private String generateRandomEmail() {
        String localPart = generateRandomString(10);
        String domain = "google.com";

        return localPart + "@" + domain;
    }

    private String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }

        return result.toString();
    }

    private static String generateRandomPESEL() {
        StringBuilder sb = new StringBuilder(PESEL_LENGTH);
        for (int i = 0; i < PESEL_LENGTH; i++) {
            sb.append(RANDOM.nextInt(10)); // Losowa cyfra od 0 do 9.
        }
        return sb.toString();
    }
}
