package ru.netology.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Ticket {
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

    public Ticket(String origin, String originName, String destination, String destinationName, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, String carrier, int stops, int price) {
        this.origin = origin;
        this.originName = originName;
        this.destination = destination;
        this.destinationName = destinationName;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.carrier = carrier;
        this.stops = stops;
        this.price = price;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public void setDestinationName(String destinationName) {
        this.destinationName = destinationName;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ru.netology.model.Ticket{" +
                "origin='" + origin + '\'' +
                ", originName='" + originName + '\'' +
                ", destination='" + destination + '\'' +
                ", destinationName='" + destinationName + '\'' +
                ", departureDate=" + departureDate +
                ", departureTime=" + departureTime +
                ", arrivalDate=" + arrivalDate +
                ", arrivalTime=" + arrivalTime +
                ", carrier='" + carrier + '\'' +
                ", stops=" + stops +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return stops == ticket.stops && price == ticket.price && Objects.equals(origin, ticket.origin) && Objects.equals(originName, ticket.originName) && Objects.equals(destination, ticket.destination) && Objects.equals(destinationName, ticket.destinationName) && Objects.equals(departureDate, ticket.departureDate) && Objects.equals(departureTime, ticket.departureTime) && Objects.equals(arrivalDate, ticket.arrivalDate) && Objects.equals(arrivalTime, ticket.arrivalTime) && Objects.equals(carrier, ticket.carrier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, originName, destination, destinationName, departureDate, departureTime, arrivalDate, arrivalTime, carrier, stops, price);
    }
}
