package com.example.tasklist.service.impl;

import com.example.tasklist.domain.MailType;
import com.example.tasklist.domain.user.User;
import com.example.tasklist.service.MailService;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import freemarker.template.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RequiredArgsConstructor
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private final Configuration configuration;
    private final JavaMailSender mailSender;

    @Override
    public void sendToEmail(
            final User user,
            final MailType type,
            final Properties params
    ) {
        switch (type) {
            case REGISTRATION -> sendRegistrationEmail(user, params);
            case REMINDER -> sendReminderEmail(user, params);
            default -> {
            }
        }
    }
    @SneakyThrows
    private void sendRegistrationEmail(
            final User user,
            final Properties params
    ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "UTF-8");

            String email = user.getUsername();
            if (email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid email for user: " + email);
            }

            helper.setTo(email);
//            helper.setFrom("noreply@yourdomain.com");
            helper.setSubject("Thank you for registration, " + user.getName());
            String emailContent;
            try {
                emailContent = getRegistrationEmailContent(user);
            } catch (Exception e) {
                log.error("Failed to render registration email template", e);
                emailContent = "Hello " + user.getName()
                        + ",\n\nThank you for registering. "
                        + "Please confirm your account using the link we sent.";
            }

            helper.setText(emailContent, true);
            mailSender.send(mimeMessage);

            log.info("Registration email sent to {}", email);
        } catch (Exception ex) {
            log.error("Error while sending registration email to {}: {}", user.getUsername(), ex.getMessage(), ex);
            throw ex;
        }
    }

    @SneakyThrows
    private void sendReminderEmail(
            final User user,
            final Properties params
    ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,
                false,
                "UTF-8");
        helper.setSubject("You have task to do in 1 hour");
        helper.setTo(user.getUsername());
        String emailContent = getReminderEmailContent(user, params);
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    private String getRegistrationEmailContent(User user) {
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getName());
        model.put("confirmationLink","http://localhost:8081/api/v1/auth/confirm-account?token="+user.getConfirmationToken());
        configuration.getTemplate("register.ftlh")
                .process(model, writer);
        return writer.getBuffer().toString();
    }

    @SneakyThrows
    private String getReminderEmailContent(User user, Properties properties) {
        StringWriter writer = new StringWriter();
        Map<String, Object> model = new HashMap<>();
        model.put("name", user.getName());
        model.put("title", properties.getProperty("task.title"));
        model.put("description", properties.getProperty("task.description"));
        configuration.getTemplate("reminder.ftlh")
                .process(model, writer);
        return writer.getBuffer().toString();
    }
}