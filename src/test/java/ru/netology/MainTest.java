package ru.netology;

import org.junit.jupiter.api.Test;
import ru.netology.dto.TicketDraft;
import ru.netology.model.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.Main.*;

class MainTest {
    @Test
    void test_readStringFromJson_success() {
        String expected = "{  \"test_1\": \"test1\",  \"test_2\": \"test2\"}";

        String result = readStringFromJson("src/test/resources/test.json");

        assertEquals(expected, result);
    }

    @Test
    void test_readStringFromJson_doesNotThrowException_notExistFile() {
        assertDoesNotThrow(() -> {
            readStringFromJson("not_exist_file.json");
        });
    }

    @Test
    void test_readStringFromJson_nullResult_notExistFile() {
        String result = readStringFromJson("not_exist_file.json");
        assertNull(result);
    }

    @Test
    void test_convertTicketDraftListToTicketList_success_case_1() {
        List<TicketDraft> draftTicketsList = new ArrayList<>();
        draftTicketsList.add(new TicketDraft("origin", "originName",
                "destination", "destinationName",
                "13.03.13", "10:10",
                "13.03.13", "12:10",
                "carrier", 1, 1000));

        List<Ticket> expected = new ArrayList<>();
        expected.add(new Ticket("origin", "originName",
                "destination", "destinationName",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(10, 10),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(12, 10),
                "carrier", 1, 1000));

        List<Ticket> result = convertTicketDraftListToTicketList(draftTicketsList);

        assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    }

    @Test
    void test_convertTicketDraftListToTicketList_success_case_2() {
        List<TicketDraft> draftTicketsList = new ArrayList<>();
        draftTicketsList.add(new TicketDraft("origin", "originName",
                "destination", "destinationName",
                "13.03.13", "9:10",
                "13.03.13", "12:10",
                "carrier", 1, 1000));

        List<Ticket> expected = new ArrayList<>();
        expected.add(new Ticket("origin", "originName",
                "destination", "destinationName",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(9, 10),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(12, 10),
                "carrier", 1, 1000));

        List<Ticket> result = convertTicketDraftListToTicketList(draftTicketsList);

        assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    }

    @Test
    void test_convertTicketDraftListToTicketList_IllegalStateException_null_field() {
        List<TicketDraft> draftTicketsList = new ArrayList<>();
        draftTicketsList.add(new TicketDraft(null, "originName",
                "destination", "destinationName",
                "13.03.13", "9:10",
                "13.03.13", "12:10",
                "carrier", 1, 1000));

        assertThrows(IllegalStateException.class,
                () -> {
                    convertTicketDraftListToTicketList(draftTicketsList);
                });
    }

    @Test
    void test_convertTicketDraftListToTicketList_DateTimeParseException_incorrect_data_format() {
        List<TicketDraft> draftTicketsList = new ArrayList<>();
        draftTicketsList.add(new TicketDraft("origin", "originName",
                "destination", "destinationName",
                "13/03/13", "9:10",
                "13.03.13", "12:10",
                "carrier", 1, 1000));

        assertThrows(DateTimeParseException.class,
                () -> {
                    convertTicketDraftListToTicketList(draftTicketsList);
                });
    }

    @Test
    void test_convertTicketDraftListToTicketList_IllegalArgumentException_incorrect_time_format() {
        List<TicketDraft> draftTicketsList = new ArrayList<>();
        draftTicketsList.add(new TicketDraft("origin", "originName",
                "destination", "destinationName",
                "13.03.13", "9:10",
                "13.03.13", "12:10:10",
                "carrier", 1, 1000));

        assertThrows(IllegalArgumentException.class,
                () -> {
                    convertTicketDraftListToTicketList(draftTicketsList);
                });
    }

    @Test
    void test_checkFlightRoute_success_case_1() {
        String departureCity = "Владивосток";
        String arrivalCity = "Тель-Авив";
        List<Ticket> ticketsList = new ArrayList<>();
        ticketsList.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(9, 10),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(12, 10),
                "carrier", 1, 1000));
        ticketsList.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 4, 14),
                LocalTime.of(10, 10),
                LocalDate.of(2013, 4, 14),
                LocalTime.of(13, 40),
                "carrier", 0, 2000));
        ticketsList.add(new Ticket("origin", "Москва",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 5, 15),
                LocalTime.of(10, 10),
                LocalDate.of(2013, 5, 15),
                LocalTime.of(13, 40),
                "carrier", 3, 3000));

        List<Ticket> expected = new ArrayList<>();
        expected.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(9, 10),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(12, 10),
                "carrier", 1, 1000));
        expected.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 4, 14),
                LocalTime.of(10, 10),
                LocalDate.of(2013, 4, 14),
                LocalTime.of(13, 40),
                "carrier", 0, 2000));

        List<Ticket> result = checkFlightRoute(ticketsList, departureCity, arrivalCity);

        assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    }

    @Test
    void test_checkFlightRoute_success_case_2() {
        String departureCity = "Владивосток";
        String arrivalCity = "Тель-Авив";
        List<Ticket> ticketsList = new ArrayList<>();
        ticketsList.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(9, 10),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(12, 10),
                "carrier", 1, 1000));
        ticketsList.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 4, 14),
                LocalTime.of(10, 10),
                LocalDate.of(2013, 4, 14),
                LocalTime.of(13, 40),
                "carrier", 0, 2000));

        List<Ticket> expected = new ArrayList<>();
        expected.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(9, 10),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(12, 10),
                "carrier", 1, 1000));
        expected.add(new Ticket("origin", "Владивосток",
                "destination", "Тель-Авив",
                LocalDate.of(2013, 4, 14),
                LocalTime.of(10, 10),
                LocalDate.of(2013, 4, 14),
                LocalTime.of(13, 40),
                "carrier", 0, 2000));

        List<Ticket> result = checkFlightRoute(ticketsList, departureCity, arrivalCity);

        assertTrue(expected.size() == result.size() && expected.containsAll(result) && result.containsAll(expected));
    }

    @Test
    void test_checkFlightRoute_success_case_empty_list() {
        String departureCity = "Владивосток";
        String arrivalCity = "Тель-Авив";
        List<Ticket> ticketsList = Collections.emptyList();

        List<Ticket> result = checkFlightRoute(ticketsList, departureCity, arrivalCity);

        assertEquals(0, result.size());
    }

    @Test
    void test_checkFlightRoute_NullPointerException() {
        String departureCity = "Владивосток";
        String arrivalCity = "Тель-Авив";
        assertThrows(NullPointerException.class,
                () -> {
                    checkFlightRoute(null, departureCity, arrivalCity);
                });
    }
}