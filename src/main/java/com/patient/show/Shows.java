package com.patient.show;

import com.patient.PatientAppointment;

import java.time.format.DateTimeFormatter;

public class Shows
{

    public static void byConsole(PatientAppointment appointment)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
        String appTime = formatter.format(appointment.getAppointmentDateTime());
        System.out.println(
            "Date = " + appTime +
                " - " + appointment.getPatientFirstName() +
                " " + appointment.getPatientLastName() +
                ", Doctor: " + appointment.getDoctor().getName());
    }

    public static void notAppointments()
    {
        System.out.println("-- Sorry you do not have any appointment assigned");
    }
}
