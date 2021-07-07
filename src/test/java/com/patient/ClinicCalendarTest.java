package com.patient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClinicCalendarTest
{

    private ClinicCalendar calendar;

    @BeforeEach
    void init()
    {
        calendar = new ClinicCalendar(LocalDate.now());
    }

    @Test
    void allowEntryOfAnAppointment()
    {
        calendar.addAppointment(
            "Jim",
            "Weaver",
            "09/01/2018 2:00 pm",
            "avery");
        List<PatientAppointment> appointments = calendar.getAppointments();

        assertNotNull(appointments);
        assertEquals(1, appointments.size());

        PatientAppointment enteredAppt = appointments.get(0);

        assertAll(
            () -> assertEquals("Jim", enteredAppt.getPatientFirstName()),
            () -> assertEquals("Weaver", enteredAppt.getPatientLastName()),
            () -> assertSame(Doctor.avery, enteredAppt.getDoctor()),
            () -> assertEquals("9/1/2018 02:00 PM",
                enteredAppt.getAppointmentDateTime()
                    .format(DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a"))));

    }

    @Test
    void createAnAppointmentByPassingAsDateToday()
    {
        calendar.addAppointment(
            "Jim",
            "Weaver",
            "today 02:00 pm",
            "avery");

        assertEquals(1, calendar.getAppointments().size());
    }

    @Test
    void returnTrueForHasAppointmentsIfThereAreAppointments()
    {
        calendar.addAppointment(
            "Jim",
            "Weaver",
            "09/01/2018 2:00 pm",
            "avery");

        assertTrue(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnFalseForHasAppointmentsIfThereAreNoAppointments()
    {
        assertFalse(calendar.hasAppointment(LocalDate.of(2018, 9, 1)));
    }

    @Test
    void returnCurrentDaysAppointments()
    {
        calendar.addAppointment(
            "Pablo",
            "Hernandez",
            "today 02:00 pm",
            "avery");

        calendar.addAppointment(
            "Lucero",
            "Facio",
            "07/13/2021 02:00 pm",
            "avery");

        calendar.addAppointment(
            "Thiago",
            "Hernandez",
            "today 02:00 pm",
            "avery");

        assertEquals(2, calendar.getTodayAppointments().size());
    }

    @Nested
    @DisplayName("return tomorrow appointments")
    class TodayAppointments
    {
        @Test
        void whenThereAreNone()
        {
            List<PatientAppointment> appointmentList = calendar.getUpcomingAppointments();
            assertEquals(0, appointmentList.size());
        }

        @Test
        void whenThereSomePastAndFuture()
        {
            calendar.addAppointment("Pablo", "Hernandez",
                "today 2:00 pm", "avery");
            calendar.addAppointment("Seda", "Irwin",
                "today 4:00 pm", "avery");
            calendar.addAppointment("Nele", "Thompson",
                "07/08/2021 2:00 pm", "avery");

            assertEquals(1, calendar.getUpcomingAppointments().size());
        }
    }

}