package com.patient.notifier;

import com.patient.ClinicCalendar;
import com.patient.PatientAppointment;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TodayAppointmentNotifier
{
    private final ClinicCalendar calendar;
    private final EmailNotifier notifier;

    public TodayAppointmentNotifier(ClinicCalendar calendar, EmailNotifier notifier)
    {
        this.notifier = notifier;
        this.calendar = calendar;
    }

    public void run()
    {
        calendar.getTodayAppointments().forEach(this::sendNotificationForAppointment);
    }

    private void sendNotificationForAppointment(PatientAppointment patientAppointment)
    {
        String email = patientAppointment.getPatientLastName().toLowerCase() +
            patientAppointment.getPatientFirstName().toLowerCase() + "@gmail.com";
        notifier.sendNotification("Appointment Reminder",
                buildMessageBody(patientAppointment), email);
    }

    private String buildMessageBody(PatientAppointment appt)
    {
        return "You have an appointment tomorrow at "
            + appt.getAppointmentDateTime().format(DateTimeFormatter.ofPattern("h:mm a", Locale.US))
            + " with Dr. "
            + appt.getDoctor().getName() + ".";
    }
}
