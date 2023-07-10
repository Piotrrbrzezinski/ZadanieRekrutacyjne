package com.rekrutacja.zadanie.repository;

import com.rekrutacja.zadanie.model.entitys.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}