package com.patient.notifier;

import com.patient.ClinicCalendar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("TodayAppointmentNotifier should")
class TodayAppointmentNotifierTest
{

    private EmailNotifierTestMock emailDouble;

    @BeforeEach
    void init()
    {
        emailDouble = new EmailNotifierTestMock();
    }

    @DisplayName("you must return the correct number of appointments with the date " +
        "set in the current")
    @Test
    void sendNotificationWithCorrectFormat()
    {
        ClinicCalendar calendar = new ClinicCalendar(LocalDate.now());

        calendar.addAppointment(
            "Pablo",
            "Hernandez",
            "today 2:00 pm",
            "avery"
        );

        TodayAppointmentNotifier notifier = new TodayAppointmentNotifier(calendar, emailDouble);
        notifier.run();

        assertEquals(1, emailDouble.receivedMessages.size());

        EmailNotifierTestMock.Message expectedMessage = emailDouble.receivedMessages.get(0);
        assertAll(
            () -> assertEquals("hernandezpablo@gmail.com", expectedMessage.toAddress),
            () -> assertEquals("Appointment Reminder", expectedMessage.subject),
            () -> assertEquals("You have an appointment tomorrow at 2:00 PM" +
                " with Dr. Ralph Avery.", expectedMessage.body)
        );
    }
}