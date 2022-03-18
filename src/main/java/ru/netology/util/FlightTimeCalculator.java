package ru.netology.util;

import ru.netology.model.Ticket;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class FlightTimeCalculator {

    public int calculateDurationInMinutes(Ticket ticket) {
        LocalDateTime departure = LocalDateTime.of(ticket.getDepartureDate(), ticket.getDepartureTime());
        LocalDateTime arrival = LocalDateTime.of(ticket.getArrivalDate(), ticket.getArrivalTime());

        if (departure.isBefore(arrival)) {
            return (int) MINUTES.between(departure, arrival);
        } else if (departure.isEqual(arrival)) {
            return 0;
        } else {
            throw new IllegalArgumentException("Incorrect flight dates and times: departure can't be after arrival");
        }
    }

    public double calculateAverageInMinutes(List<Integer> dataSampling) {
        if (!dataSampling.isEmpty()) {
            long sum = 0;
            for (int travelDuration : dataSampling) {
                sum += travelDuration;
            }
            return sum / (double) dataSampling.size();
        } else {
            throw new IllegalArgumentException("Data sampling for calculating average is empty");
        }
    }

    public int calculatePercentileInMinutes(List<Integer> dataSampling, int percentile) {
        if (!dataSampling.isEmpty()) {
            if (percentile > 0 && percentile <= 100) {
                Collections.sort(dataSampling);
                int index = (int) Math.ceil(percentile / 100.0 * dataSampling.size());
                return dataSampling.get(index - 1);
            } else {
                throw new IllegalArgumentException("Percentile for calculating percentile value can't be less or equal 0 or more than 100");
            }
        } else {
            throw new IllegalArgumentException("Data sampling percentile is empty");
        }
    }
}
