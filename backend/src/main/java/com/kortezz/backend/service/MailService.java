package com.kortezz.backend.service;

import com.kortezz.backend.entity.Mail;
import com.kortezz.backend.repository.MailRepository;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
class MailService {

    private JavaMailSender javaMailSender;
    private MailRepository mailRepository;

    @Retryable
    @Async
    public void sendMail(Mail mail) {
        sendSimpleMessage(mail);
    }

    @Recover
    public void recoverSendingMail(Exception e, Mail mail) {
        mail.setIsSent(false);
        mailRepository.save(mail);
    }

    @Scheduled(cron = "* */5 * ? * *")
    public void verifyNotSentMail() {
        List<Mail> allNotSentMails = mailRepository.findAllNotSentMails();
        if(allNotSentMails.size() > 0) {
            allNotSentMails.forEach(i -> i.setIsSent(true));
            mailRepository.saveAll(allNotSentMails);
            allNotSentMails.forEach(this::sendMail);
        }
    }

    private void sendSimpleMessage(Mail mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getFrom());
        message.setTo(mail.getTo());
        message.setSubject(mail.getSubject());
        message.setText(mail.getText());
        javaMailSender.send(message);
    }

}
