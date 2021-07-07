package com.patient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class ClinicMain
{
    private static ClinicCalendar calendar;

    public static void main(String[] args)
    {
        calendar = new ClinicCalendar(LocalDate.now());
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Patient Intake Computer System!\n\n");
        String lastOption = "";
        while (!lastOption.equalsIgnoreCase("x"))
        {
            lastOption = displayMenu(scanner);
        }
        System.out.println("\nExiting System...\n");
    }

    private static String displayMenu(Scanner scanner)
    {
        System.out.println("Please select an option:");
        System.out.println("1. Enter a Patient Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. View Today's Appointments");
        System.out.println("X. Exit System");
        System.out.println("Option: ");
        String option = scanner.next();

        switch (option)
        {
            case "1":
                performPatientEntry(scanner);
                return option;
            case "2":
                performAllAppointments();
                return option;
            case "3":
                performTodayAppointments();
                return option;
            default:
                System.out.println("Invalid option, please re-enter");
                return option;
        }
    }

    private static void performTodayAppointments()
    {
        List<PatientAppointment> todayAppointments = calendar.getTodayAppointments();
        todayAppointments.forEach(patientAppointment ->
        {
            System.out.println(
                    "Date = " + patientAppointment.getAppointmentDateTime() +
                            " - " + patientAppointment.getPatientFirstName() +
                            " " + patientAppointment.getPatientLastName() +
                            ", Doctor: " + patientAppointment.getDoctor().getName()
            );
        });
    }

    private static void performPatientEntry(Scanner scanner)
    {
        scanner.nextLine();
        System.out.println("\n\nPlease Enter Appointment Info:");
        System.out.println("  Patient Last Name: ");
        String lastName = scanner.nextLine();

        System.out.println("  Patient First Name: ");
        String firstName = scanner.nextLine();

        System.out.println("Appointment Date (M/d/yyyy h:m a): ");
        String when = scanner.nextLine();

        System.out.println("  Doctor Last Name: ");
        String doc = scanner.nextLine();

        try
        {
            calendar.addAppointment(firstName, lastName, when, doc);
        } catch (Throwable t)
        {
            System.out.println("Error!  " + t.getMessage());
            return;
        }
        System.out.println("Patient entered successfully.\n\n");
    }

    private static void performAllAppointments()
    {
        System.out.println("\n\n All Appointments in System:");
        for (PatientAppointment appointment : calendar.getAppointments())
        {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy hh:mm a");
            String appTime = formatter.format(appointment.getAppointmentDateTime());
            System.out.println(
                    "Date = " + appTime +
                            " - " + appointment.getPatientFirstName() +
                            " " + appointment.getPatientLastName() +
                            ", Doctor: " + appointment.getDoctor().getName());
        }
        System.out.println("\nPress any key yo continue...");
    }
}
