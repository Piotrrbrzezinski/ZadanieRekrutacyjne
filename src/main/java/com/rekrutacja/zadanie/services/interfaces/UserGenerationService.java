package com.rekrutacja.zadanie.services.interfaces;

import org.springframework.transaction.annotation.Transactional;

public interface UserGenerationService {
    @Transactional
    void generateUsersAndContactInfos();
}
