package com.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientAppointment
{

    private final String patientFirstName;
    private final String patientLastName;
    private final LocalDateTime appointmentDateTime;
    private final Doctor doctor;

    public PatientAppointment(String patientFirstName, String patientLastName,
                              LocalDateTime appointmentDateTime, Doctor doctor)
    {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.appointmentDateTime = appointmentDateTime;
        this.doctor = doctor;

    }

    public String getPatientFirstName()
    {
        return patientFirstName;
    }

    public String getPatientLastName()
    {
        return patientLastName;
    }

    public LocalDateTime getAppointmentDateTime()
    {
        return appointmentDateTime;
    }


    /**
     * @return The {@link Doctor} Object assigned to this class
     */
    public Doctor getDoctor()
    {
        return doctor;
    }

    @Override
    public String toString()
    {
        return "PatientAppointment{" +
                "patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", appointmentDateTime=" + appointmentDateTime +
                ", doctor=" + doctor +
                '}';
    }

    /**
     * Returns the {@link LocalDateTime} field with a {@link LocalDate} format
     *
     * @return A {@link LocalDate} Object
     */
    public LocalDateTime getAppointmentLocalDate()
    {
        return appointmentDateTime;
    }
}
