package ru.netology.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.model.Ticket;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FlightTimeCalculatorTest {
    static FlightTimeCalculator flightTimeCalculator;

    @BeforeAll
    static void beforeAll() {
        flightTimeCalculator = new FlightTimeCalculator();
    }

    @Test
    void test_calculateDurationInMinutes_success_case1() {
        Ticket ticket = new Ticket("VVO", "Владивосток",
                "TLV", "Тель-Авив",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(9, 10),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(12, 10),
                "S7", 1, 30000);

        int expected = 180;
        int result = flightTimeCalculator.calculateDurationInMinutes(ticket);

        assertEquals(expected, result);
    }

    @Test
    void test_calculateDurationInMinutes_success_case2() {
        Ticket ticket = new Ticket("VVO", "Владивосток",
                "TLV", "Тель-Авив",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(23, 0),
                LocalDate.of(2013, 3, 14),
                LocalTime.of(9, 0),
                "S7", 1, 30000);

        int expected = 600;
        int result = flightTimeCalculator.calculateDurationInMinutes(ticket);

        assertEquals(expected, result);
    }

    @Test
    void test_calculateDurationInMinutes_success_case2_equal_date_time() {
        Ticket ticket = new Ticket("VVO", "Владивосток",
                "TLV", "Тель-Авив",
                LocalDate.of(2013, 3, 13),
                LocalTime.of(23, 0),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(23, 0),
                "S7", 1, 30000);

        int expected = 0;
        int result = flightTimeCalculator.calculateDurationInMinutes(ticket);

        assertEquals(expected, result);
    }

    @Test
    void test_calculateDurationInMinutes_IllegalArgumentException() {
        Ticket ticket = new Ticket("VVO", "Владивосток",
                "TLV", "Тель-Авив",
                LocalDate.of(2013, 3, 14),
                LocalTime.of(9, 0),
                LocalDate.of(2013, 3, 13),
                LocalTime.of(23, 0),
                "S7", 1, 30000);

        assertThrows(IllegalArgumentException.class,
                () -> {
                    flightTimeCalculator.calculateDurationInMinutes(ticket);
                });
    }

    @Test
    void test_calculateDurationInMinutes_NullPointerException() {
        assertThrows(NullPointerException.class,
                () -> {
                    flightTimeCalculator.calculateDurationInMinutes(null);
                });
    }

    @Test
    void test_calculateAverageInMinutes_success_case1() {
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(201);
        dataSampling.add(300);
        dataSampling.add(457);

        double expected = 319.3333333333333;
        double result = flightTimeCalculator.calculateAverageInMinutes(dataSampling);

        assertEquals(expected, result);
    }

    @Test
    void test_calculateAverageInMinutes_success_case2() {
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(200);
        dataSampling.add(300);

        double expected = 250.0;
        double result = flightTimeCalculator.calculateAverageInMinutes(dataSampling);

        assertEquals(expected, result);
    }

    @Test
    void test_calculateAverageInMinutes_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    flightTimeCalculator.calculateAverageInMinutes(Collections.emptyList());
                });
    }

    @Test
    void test_calculateAverageInMinutes_NullPointerException() {
        assertThrows(NullPointerException.class,
                () -> {
                    flightTimeCalculator.calculateAverageInMinutes(null);
                });
    }

    @Test
    void test_calculatePercentileInMinutes_success_case1() {
        int percentile = 90;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(200);
        dataSampling.add(300);
        dataSampling.add(400);
        dataSampling.add(500);

        int expected = 500;
        int result = flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);

        assertEquals(expected, result);
    }

    @Test
    void test_calculatePercentileInMinutes_success_case2() {
        int percentile = 90;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(600);
        dataSampling.add(300);
        dataSampling.add(400);
        dataSampling.add(300);
        dataSampling.add(600);
        dataSampling.add(700);
        dataSampling.add(800);
        dataSampling.add(900);
        dataSampling.add(100);

        int expected = 800;
        int result = flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);

        assertEquals(expected, result);
    }

    @Test
    void test_calculatePercentileInMinutes_success_case3() {
        int percentile = 3;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(600);
        dataSampling.add(300);
        dataSampling.add(400);
        dataSampling.add(300);
        dataSampling.add(600);
        dataSampling.add(700);
        dataSampling.add(800);
        dataSampling.add(900);
        dataSampling.add(100);

        int expected = 100;
        int result = flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);

        assertEquals(expected, result);
    }

    @Test
    void test_calculatePercentileInMinutes_success_case4() {
        int percentile = 95;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(600);
        dataSampling.add(300);

        int expected = 600;
        int result = flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);

        assertEquals(expected, result);
    }

    @Test
    void test_calculatePercentileInMinutes_success_case5() {
        int percentile = 100;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(600);
        dataSampling.add(300);
        dataSampling.add(400);
        dataSampling.add(300);
        dataSampling.add(600);
        dataSampling.add(700);
        dataSampling.add(800);
        dataSampling.add(900);
        dataSampling.add(100);

        int expected = 900;
        int result = flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);

        assertEquals(expected, result);
    }

    @Test
    void test_calculatePercentileInMinutes_IllegalArgumentException_negative_percentile() {
        int percentile = -90;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(600);
        dataSampling.add(300);
        dataSampling.add(400);
        dataSampling.add(300);
        dataSampling.add(600);
        dataSampling.add(700);
        dataSampling.add(800);
        dataSampling.add(900);
        dataSampling.add(100);

        assertThrows(IllegalArgumentException.class,
                () -> {
                    flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);
                });
    }

    @Test
    void test_calculatePercentileInMinutes_IllegalArgumentException_zero_percentile() {
        int percentile = 0;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(600);
        dataSampling.add(300);
        dataSampling.add(400);
        dataSampling.add(300);
        dataSampling.add(600);
        dataSampling.add(700);
        dataSampling.add(800);
        dataSampling.add(900);
        dataSampling.add(100);

        assertThrows(IllegalArgumentException.class,
                () -> {
                    flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);
                });
    }

    @Test
    void test_calculatePercentileInMinutes_IllegalArgumentException_more_than_100_percentile() {
        int percentile = 110;
        List<Integer> dataSampling = new ArrayList<>();
        dataSampling.add(100);
        dataSampling.add(600);
        dataSampling.add(300);
        dataSampling.add(400);
        dataSampling.add(300);
        dataSampling.add(600);
        dataSampling.add(700);
        dataSampling.add(800);
        dataSampling.add(900);
        dataSampling.add(100);

        assertThrows(IllegalArgumentException.class,
                () -> {
                    flightTimeCalculator.calculatePercentileInMinutes(dataSampling, percentile);
                });
    }

    @Test
    void test_calculatePercentileInMinutes_IllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    flightTimeCalculator.calculatePercentileInMinutes(Collections.emptyList(), 90);
                });
    }

    @Test
    void test_calculatePercentileInMinutes_NullPointerException() {
        assertThrows(NullPointerException.class,
                () -> {
                    flightTimeCalculator.calculatePercentileInMinutes(null, 90);
                });
    }
}