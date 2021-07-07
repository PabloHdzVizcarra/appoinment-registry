package com.patient;

import com.patient.show.Shows;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

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

    // TODO: 7/7/21 step 5 show current days appointments
    // TODO: 7/7/21 step 6 show tomorrow appointments
    private static String displayMenu(Scanner scanner)
    {
        System.out.println("Please select an option:");
        System.out.println("1. Enter a Patient Appointment");
        System.out.println("2. View All Appointments");
        System.out.println("3. View Today's Appointments");
        System.out.println("4. Enter Patient Height Weight");
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
            case "4":
                performHeightWeight(scanner);
            default:
                System.out.println("Invalid option, please re-enter");
                return option;
        }
    }

    private static void performHeightWeight(Scanner scanner)
    {
        scanner.nextLine();
        System.out.println("Enter patient height nd weight for today's appointment");

        System.out.println("  Patient Last Name: ");
        String lastname = scanner.nextLine();

        System.out.println("  Patient First Name: ");
        String firstname = scanner.nextLine();

        PatientAppointment appointment = (PatientAppointment) findPatientAppointment(lastname,
            firstname)
            .orElse(null);

        if (appointment != null)
        {
            System.out.println("  Height in Kilos: ");
            int inches = scanner.nextInt();

            System.out.println("  Weight in Meters");
            int pounds = scanner.nextInt();

            double roundedToTwoPlaces = BMICalculator.calculateBmi(inches, pounds);
            appointment.setBmi(roundedToTwoPlaces);
            System.out.println("Set patient BMI to " + roundedToTwoPlaces + "\n\n");
        }
        else
        {
            System.out.println("Patient not found.\n\n");
        }
    }

    private static Optional<Object> findPatientAppointment(String lastname, String firstname)
    {
        List<PatientAppointment> collect = calendar.getAppointments().stream()
            .filter(patientAppointment ->
                patientAppointment.getPatientFirstName().equals(firstname) &&
                    patientAppointment.getPatientLastName().equals(lastname))
            .collect(Collectors.toList());
        return Optional.of(collect.get(0));
    }

    private static void performTodayAppointments()
    {
        List<PatientAppointment> todayAppointments = calendar.getTodayAppointments();
        todayAppointments.forEach(patientAppointment ->
            System.out.println(
                "Date = " + patientAppointment.getAppointmentDateTime() +
                    " - " + patientAppointment.getPatientFirstName() +
                    " " + patientAppointment.getPatientLastName() +
                    ", Doctor: " + patientAppointment.getDoctor().getName()
            ));
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
        System.out.println("\n\n All Appointments in System: \n");
        if (calendar.getTodayAppointments().size() == 0)
        {
            Shows.notAppointments();
        }
        else
        {
            calendar.getAppointments().forEach(Shows::byConsole);
        }
        System.out.println("\nPress any key yo continue... \n");
    }

}
