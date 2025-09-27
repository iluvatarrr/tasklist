package com.example.tasklist.service;

import com.example.tasklist.domain.MailType;
import com.example.tasklist.domain.user.User;
import org.springframework.mail.SimpleMailMessage;

import java.util.Properties;

public interface MailService {
    void sendToEmail(
            User user,
            MailType type,
            Properties params
    );
}
