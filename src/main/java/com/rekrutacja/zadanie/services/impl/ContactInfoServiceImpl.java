package com.rekrutacja.zadanie.services.impl;

import com.rekrutacja.zadanie.model.entitys.ContactInfo;
import com.rekrutacja.zadanie.repository.ContactInfoRepository;
import com.rekrutacja.zadanie.services.interfaces.ContactInfoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    public ContactInfo createContactInfo(ContactInfo contactInfo){
        return contactInfoRepository.save(contactInfo);
    }
}
