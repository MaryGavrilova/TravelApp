package ru.netology.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicketBuilderTest {
    @Test
    void test_build_success_case() {
        TicketBuilder ticketBuilder = new TicketBuilder()
                .setOrigin("VVO")
                .setOriginName("Владивосток")
                .setDestination("TLV")
                .setDestinationName("Тель-Авив")
                .setDepartureDate("12.05.18")
                .setDepartureTime("16:20")
                .setArrivalDate("12.05.18")
                .setArrivalTime("22:10")
                .setCarrier("TK")
                .setStops(3)
                .setPrice(12400);

        Ticket expected = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив",
                LocalDate.of(2018, 5, 12), LocalTime.of(16, 20),
                LocalDate.of(2018, 5, 12), LocalTime.of(22, 10),
                "TK", 3, 12400);

        Ticket result = ticketBuilder.build();

        assertEquals(expected, result);
    }

    @Test
    void test_build_IllegalStateException() {
        TicketBuilder ticketBuilder = new TicketBuilder()
                .setOriginName("Владивосток")
                .setDestination("TLV")
                .setDestinationName("Тель-Авив")
                .setDepartureDate("12.05.18")
                .setDepartureTime("16:20")
                .setArrivalDate("12.05.18")
                .setArrivalTime("22:10")
                .setCarrier("TK")
                .setStops(3)
                .setPrice(12400);

        assertThrows(IllegalStateException.class,
                ticketBuilder::build);
    }

    @Test
    void test_setDepartureTime_success_case_LONG_TIME_PATTERN() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String departureTimeStr = "22:10";

        TicketBuilder expected = new TicketBuilder();
        expected.departureTime = LocalTime.of(22, 10);

        TicketBuilder result = ticketBuilder.setDepartureTime(departureTimeStr);

        assertEquals(expected, result);
    }

    @Test
    void test_setDepartureTime_success_case_SHORT_TIME_PATTERN() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String departureTimeStr = "9:10";

        TicketBuilder expected = new TicketBuilder();
        expected.departureTime = LocalTime.of(9, 10);

        TicketBuilder result = ticketBuilder.setDepartureTime(departureTimeStr);

        assertEquals(expected, result);
    }

    @Test
    void test_setDepartureTime_IllegalArgumentException() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String departureTimeStr = "10:10:10";

        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketBuilder.setDepartureTime(departureTimeStr);
                });
    }

    @Test
    void test_setDepartureTime_NullPointerException() {
        TicketBuilder ticketBuilder = new TicketBuilder();

        assertThrows(NullPointerException.class,
                () -> {
                    ticketBuilder.setDepartureTime(null);
                });
    }

    @Test
    void test_setArrivalTime_success_case_LONG_TIME_PATTERN() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String arrivalTimeStr = "22:10";

        TicketBuilder expected = new TicketBuilder();
        expected.arrivalTime = LocalTime.of(22, 10);

        TicketBuilder result = ticketBuilder.setArrivalTime(arrivalTimeStr);

        assertEquals(expected, result);
    }

    @Test
    void test_setArrivalTime_success_case_SHORT_TIME_PATTERN() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String arrivalTimeStr = "9:10";

        TicketBuilder expected = new TicketBuilder();
        expected.arrivalTime = LocalTime.of(9, 10);

        TicketBuilder result = ticketBuilder.setArrivalTime(arrivalTimeStr);

        assertEquals(expected, result);
    }

    @Test
    void test_setArrivalTime_IllegalArgumentException() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String arrivalTimeStr = "10:10:10";

        assertThrows(IllegalArgumentException.class,
                () -> {
                    ticketBuilder.setArrivalTime(arrivalTimeStr);
                });
    }

    @Test
    void test_setArrivalTime_NullPointerException() {
        TicketBuilder ticketBuilder = new TicketBuilder();

        assertThrows(NullPointerException.class,
                () -> {
                    ticketBuilder.setArrivalTime(null);
                });
    }

    @Test
    void test_setDepartureDate_success_case() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String departureDateStr = "12.05.18";

        TicketBuilder expected = new TicketBuilder();
        expected.departureDate = LocalDate.of(2018, 5, 12);

        TicketBuilder result = ticketBuilder.setDepartureDate(departureDateStr);

        assertEquals(expected, result);
    }

    @Test
    void test_setDepartureDate_DateTimeParseException() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String departureDateStr = "12.05.2018";

        assertThrows(DateTimeParseException.class,
                () -> {
                    ticketBuilder.setDepartureDate(departureDateStr);

                });
    }

    @Test
    void test_setDepartureDate_NullPointerException() {
        TicketBuilder ticketBuilder = new TicketBuilder();

        assertThrows(NullPointerException.class,
                () -> {
                    ticketBuilder.setDepartureDate(null);

                });
    }

    @Test
    void test_setArrivalDate_success_case() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String arrivalDateStr = "12.05.18";

        TicketBuilder expected = new TicketBuilder();
        expected.arrivalDate = LocalDate.of(2018, 5, 12);

        TicketBuilder result = ticketBuilder.setArrivalDate(arrivalDateStr);

        assertEquals(expected, result);
    }

    @Test
    void test_setArrivalDate_DateTimeParseException() {
        TicketBuilder ticketBuilder = new TicketBuilder();
        String arrivalDateStr = "12.05.2018";

        assertThrows(DateTimeParseException.class,
                () -> {
                    ticketBuilder.setArrivalDate(arrivalDateStr);

                });
    }

    @Test
    void test_setArrivalDate_NullPointerException() {
        TicketBuilder ticketBuilder = new TicketBuilder();

        assertThrows(NullPointerException.class,
                () -> {
                    ticketBuilder.setArrivalDate(null);

                });
    }
}