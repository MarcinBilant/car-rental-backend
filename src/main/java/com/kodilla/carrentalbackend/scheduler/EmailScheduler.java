package com.kodilla.carrentalbackend.scheduler;

import com.kodilla.carrentalbackend.config.AdminConfig;
import com.kodilla.carrentalbackend.domain.Mail;
import com.kodilla.carrentalbackend.repository.ReservationRepository;
import com.kodilla.carrentalbackend.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {
    @Autowired
    private SimpleEmailService simpleEmailService;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private AdminConfig adminConfig;


    private static final String SUBJECT = "Reservation: Once a day email";


    @Scheduled(cron = "0 0 10 * * * ")
    public void sendInformationEmail() {
        long size = reservationRepository.count();
        if (size == 1) {
            simpleEmailService.send(new Mail(adminConfig.getAdminMail(), null, SUBJECT,
                    "Currently in car rental system you got: " + size + " reservation"));
        } else {
            simpleEmailService.send(new Mail(adminConfig.getAdminMail(), null, SUBJECT,
                    "Currently in car rental system you got: " + size + " reservations"));
        }
    }

}
