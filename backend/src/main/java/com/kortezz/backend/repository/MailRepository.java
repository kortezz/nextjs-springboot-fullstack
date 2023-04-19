package com.kortezz.backend.repository;

import com.kortezz.backend.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MailRepository extends JpaRepository<Mail, Long> {
    @Query("SELECT e FROM  Mail e WHERE e.isSent=false")
    List<Mail> findAllNotSentMails();
}
