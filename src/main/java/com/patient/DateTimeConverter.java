package com.patient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateTimeConverter
{
    public static LocalDateTime convertStringToDateTime(String dateTimeString, LocalDate today)
    {
        LocalDateTime localDateTime;
        if (dateTimeString.toUpperCase().startsWith("TODAY"))
        {
            DateTimeFormatter dateTimeStringFormatter = DateTimeFormatter.ofPattern("h:m a");
            String day = dateTimeString
                .toUpperCase()
                .replace("TODAY", "")
                .trim();
            localDateTime = LocalDateTime.of(
                today,
                LocalTime.parse(
                    day,
                    dateTimeStringFormatter));
        } else
        {
            try
            {
                localDateTime = LocalDateTime.parse(dateTimeString.toUpperCase(),
                    DateTimeFormatter.ofPattern("M/d/yyyy h:mm a", Locale.US));
            } catch (Throwable t)
            {
                throw new RuntimeException("Unable to create date time from : [" +
                    dateTimeString.toUpperCase() + "], please enter with format [M/d/yyyy h:mm " +
                    "am/pm]");
            }
        }
        return localDateTime;
    }
}
