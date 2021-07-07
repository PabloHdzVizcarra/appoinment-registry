package com.patient;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateTimeConverter should")
class DateTimeConverterShould
{

    @Nested
    @DisplayName("convert string with 'today' keyword")
    class TodayTests {

        @Test
        @DisplayName("correctly")
        void convertTodayStringCorrectly()
        {
            LocalDateTime result = DateTimeConverter.convertStringToDateTime(
                "today 1:00 pm",
                LocalDate.of(2021, 8, 6));

            LocalDateTime today =
                LocalDateTime.of(2021, 8, 6, 13, 0);
            assertEquals(
                result,
                today,
                () -> "Failed to convert 'today' string to expected date time, " +
                    "today passed was: " + today);
        }

        @Test
        @DisplayName("regardless of case")
        void convertCorrectPatternDateTime()
        {
            LocalDateTime result = DateTimeConverter.convertStringToDateTime(
                "ToDay 1:00 pm",
                LocalDate.of(2021, 7, 6));

            assertEquals(
                result,
                LocalDateTime.of(2021, 7, 6, 13, 0));
        }
    }

    @Test
    @DisplayName("convert expected date time pattern in string correctly")
    void throwExceptionIfIncorrectPatternProvided()
    {
        assertThrows(RuntimeException.class, () ->
            DateTimeConverter.convertStringToDateTime("9/5/2020 100 pm",
                LocalDate.of(2020, 9, 5)));
    }

}