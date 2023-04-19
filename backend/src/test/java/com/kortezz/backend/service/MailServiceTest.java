package com.kortezz.backend.service;

import com.kortezz.backend.entity.Mail;
import com.kortezz.backend.repository.MailRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @InjectMocks
    MailService mailService;

    @Mock
    JavaMailSender javaMailSender;

    @Mock
    MailRepository mailRepository;

    private Mail mail;

    @BeforeEach
    void setUp() {
        mail = new Mail(1L, "to@a.com", "noreply@location.com",
            "Subject", "Text body", false);
    }

    @Test
    void calledSendMail_calledSendInJavaMailSender() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("noreply@location.com");
        simpleMailMessage.setTo("to@a.com");
        simpleMailMessage.setSubject("Subject");
        simpleMailMessage.setText("Text body");


        mailService.sendMail(mail);

        verify(javaMailSender, times(1)).send(simpleMailMessage);

    }

    @Test
    void calledRecoverSendingMail_saveMailDetails() {
        mailService.recoverSendingMail(new RuntimeException("ex"), mail);

        assertEquals(false, mail.getIsSent());
        verify(mailRepository, times(1)).save(mail);
    }

    @Test
    void calledVerifyNotSendMail_mailResent() {
        when(mailRepository.findAllNotSentMails()).thenReturn(Collections.singletonList(mail));

        mailService.verifyNotSentMail();

        mail.setIsSent(true);

        verify(mailRepository, times(1)).saveAll(Collections.singletonList(mail));
        verify(javaMailSender, times(1)).send((SimpleMailMessage) any());
    }
}
