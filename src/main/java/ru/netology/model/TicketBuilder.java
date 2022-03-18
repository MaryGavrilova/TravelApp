package ru.netology.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class TicketBuilder {
    public final String DATA_PATTERN = "dd.MM.yy";
    public final String SHORT_TIME_PATTERN = "H:mm";
    public final String LONG_TIME_PATTERN = "HH:mm";

    protected String origin;
    protected String originName;
    protected String destination;
    protected String destinationName;
    protected LocalDate departureDate;
    protected LocalTime departureTime;
    protected LocalDate arrivalDate;
    protected LocalTime arrivalTime;
    protected String carrier;
    protected int stops;
    protected int price;

    public TicketBuilder() {
    }

    public TicketBuilder setOrigin(String origin) {
        this.origin = origin;
        return this;
    }

    public TicketBuilder setOriginName(String originName) {
        this.originName = originName;
        return this;
    }

    public TicketBuilder setDestination(String destination) {
        this.destination = destination;
        return this;
    }

    public TicketBuilder setDestinationName(String destinationName) {
        this.destinationName = destinationName;
        return this;
    }

    public TicketBuilder setCarrier(String carrier) {
        this.carrier = carrier;
        return this;
    }

    public TicketBuilder setStops(int stops) {
        this.stops = stops;
        return this;
    }

    public TicketBuilder setPrice(int price) {
        this.price = price;
        return this;
    }

    public TicketBuilder setDepartureDate(String departureDateStr) {
        this.departureDate = LocalDate.parse(departureDateStr, DateTimeFormatter.ofPattern(DATA_PATTERN));
        return this;
    }

    public TicketBuilder setArrivalDate(String arrivalDateStr) {
        this.arrivalDate = LocalDate.parse(arrivalDateStr, DateTimeFormatter.ofPattern(DATA_PATTERN));
        return this;
    }

    public TicketBuilder setDepartureTime(String departureTimeStr) {
        if (departureTimeStr.length() == SHORT_TIME_PATTERN.length()) {
            this.departureTime = LocalTime.parse(departureTimeStr, DateTimeFormatter.ofPattern(SHORT_TIME_PATTERN));
            return this;
        } else if (departureTimeStr.length() == LONG_TIME_PATTERN.length()) {
            this.departureTime = LocalTime.parse(departureTimeStr, DateTimeFormatter.ofPattern(LONG_TIME_PATTERN));
            return this;
        } else {
            throw new IllegalArgumentException("Incorrect time format in ticket info file");
        }
    }

    public TicketBuilder setArrivalTime(String arrivalTimeStr) {
        if (arrivalTimeStr.length() == SHORT_TIME_PATTERN.length()) {
            this.arrivalTime = LocalTime.parse(arrivalTimeStr, DateTimeFormatter.ofPattern(SHORT_TIME_PATTERN));
            return this;
        } else if (arrivalTimeStr.length() == LONG_TIME_PATTERN.length()) {
            this.arrivalTime = LocalTime.parse(arrivalTimeStr, DateTimeFormatter.ofPattern(LONG_TIME_PATTERN));
            return this;
        } else {
            throw new IllegalArgumentException("Incorrect time format in ticket info file");
        }
    }

    public Ticket build() {
        if (origin == null || originName == null || destination == null || destinationName == null ||
                departureDate == null || departureTime == null || arrivalDate == null || arrivalTime == null ||
                carrier == null) {
            throw new IllegalStateException("There is not complete data about flight in ticket info file");
        }
        return new Ticket(origin, originName, destination, destinationName, departureDate, departureTime,
                arrivalDate, arrivalTime, carrier, stops, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketBuilder that = (TicketBuilder) o;
        return stops == that.stops && price == that.price && Objects.equals(origin, that.origin) && Objects.equals(originName, that.originName) && Objects.equals(destination, that.destination) && Objects.equals(destinationName, that.destinationName) && Objects.equals(departureDate, that.departureDate) && Objects.equals(departureTime, that.departureTime) && Objects.equals(arrivalDate, that.arrivalDate) && Objects.equals(arrivalTime, that.arrivalTime) && Objects.equals(carrier, that.carrier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(DATA_PATTERN, SHORT_TIME_PATTERN, LONG_TIME_PATTERN, origin, originName, destination, destinationName, departureDate, departureTime, arrivalDate, arrivalTime, carrier, stops, price);
    }
}
