package com.kodilla.carrentalbackend.service;

import com.kodilla.carrentalbackend.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import static org.mockito.Mockito.times;
import static  org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTestSuite {
    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    //@Ignore
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test1@test1.com",null, "Test", "Test message");
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1");
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        if (mail.getToCc()!= null) {
            mailMessage.setCc(mail.getToCc());
        }

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(mailMessage);

    }
}
