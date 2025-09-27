package com.example.tasklist.service;

import org.springframework.mail.SimpleMailMessage;

public interface MailService {
    void sendToEmail(SimpleMailMessage email);
}
