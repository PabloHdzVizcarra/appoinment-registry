package com.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ClinicCalendar
{
    private final List<PatientAppointment> appointments;
    private final LocalDate today;

    /**
     * @param today Current day with {@link LocalDate} format
     */
    public ClinicCalendar(LocalDate today)
    {
        this.today = today;
        this.appointments = new ArrayList<>();
    }

    /**
     * Creates a new {@link PatientAppointment} object and adds it to a list of appointments
     *
     * @param patientFirstName  The patient first name
     * @param patientSecondName The patient second name
     * @param doctorKey         Some key inside the enum {@link Doctor}
     * @param dateTime          A {@link String} with a date format "mm/dd/yyyy hh:mm am/pm"
     */
    public void addAppointment(String patientFirstName,
                               String patientSecondName,
                               String dateTime,
                               String doctorKey)
    {
        Doctor doc = Doctor.valueOf(doctorKey.toLowerCase());
        LocalDateTime localDateTime =
                DateTimeConverter.convertStringToDateTime(dateTime, today);
        PatientAppointment appointment =
                new PatientAppointment(patientFirstName, patientSecondName,
                        localDateTime, doc);
        this.appointments.add(appointment);
    }

    /**
     * Returns a {@link List} of {@link PatientAppointment} objects stored in a private field
     * inside the class, this list of objects cannot be modified from outside this class.
     *
     * @return A list with {@link PatientAppointment} objects
     */
    public List<PatientAppointment> getAppointments()
    {
        return Collections.unmodifiableList(this.appointments);
    }

    /**
     * Takes a format of a {@link LocalDate} in a String and checks if you have any appointments
     * assigned
     * at that time
     *
     * @param date A String with the format "mm/dd/yyyy hh:mm am/pm"
     * @return True if the appointment is already assigned on that schedule
     */
    public boolean hasAppointment(LocalDate date)
    {
        appointments.stream()
                .map(patientAppointment -> patientAppointment.getAppointmentDateTime().toLocalDate())
                .forEach(System.out::println);
        return appointments.stream()
                .anyMatch(appt -> appt.getAppointmentDateTime().toLocalDate().equals(date));
    }

    /**
     * Creates a {@link List} that cannot be modified, with {@link PatientAppointment}
     *  Objects that have date set today.
     *
     * @return A {@link List} of {@link PatientAppointment} that have the current date
     */
    public List<PatientAppointment> getTodayAppointments()
    {
        return this.appointments.stream()
                .filter(appt -> appt.getAppointmentLocalDate().equals(today))
                .collect(Collectors.toUnmodifiableList());
    }

    /**
     *  Creates a {@link List} that cannot be modified, with {@link PatientAppointment}
     * Objects that have field appointmentDateTime set tomorrow.
     *
     * @return A {@link List} of {@link PatientAppointment} that have the tomorrow date
     */
    public List<PatientAppointment> getUpcomingAppointments()
    {
        return appointments.stream()
            .filter(appt -> appt.getAppointmentDateTime().toLocalDate().isAfter(today))
            .collect(Collectors.toList());
    }
}
