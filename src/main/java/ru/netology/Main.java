package ru.netology;

import com.google.gson.Gson;
import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.io.input.BOMInputStream;
import ru.netology.dto.TicketDraft;
import ru.netology.dto.TicketsInfo;
import ru.netology.model.Ticket;
import ru.netology.model.TicketBuilder;
import ru.netology.util.FlightTimeCalculator;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class Main {
    public static final int QUANTITY_OF_MINUTES_IN_HOUR = 60;

    public static void main(String[] args) {
        // read application.properties
        String dataFilePath = null;
        String departureCity = null;
        String arrivalCity = null;
        int percentile = 0;

        try (InputStream is = Main.class.getResourceAsStream("/application.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            dataFilePath = properties.getProperty("tickets");
            departureCity = new String((properties.getProperty("departure")).getBytes(StandardCharsets.ISO_8859_1));
            arrivalCity = new String((properties.getProperty("arrival")).getBytes(StandardCharsets.ISO_8859_1));
            percentile = Integer.parseInt(properties.getProperty("percentile"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // read tickets.json file
        String jsonString = readStringFromJson(dataFilePath);

        if (jsonString != null) {
            // parse JSON String into list of TicketDraft objects

            TicketsInfo ticketsInfo = new Gson().fromJson(jsonString, TicketsInfo.class);
            List<TicketDraft> draftTicketsList = ticketsInfo.getTickets();

            // convert list of TicketDraft objects to list of Tickets objects
            List<Ticket> ticketsList = convertTicketDraftListToTicketList(draftTicketsList);

            // choose tickets with necessary flight rout
            List<Ticket> ticketsDataSampling = checkFlightRoute(ticketsList, departureCity, arrivalCity);

            FlightTimeCalculator flightTimeCalculator = new FlightTimeCalculator();

            // prepare list of travel durations in minutes
            List<Integer> flightDurationDataSampling = new ArrayList<>();
            for (Ticket ticket : ticketsDataSampling) {
                int flightTime = flightTimeCalculator.calculateDurationInMinutes(ticket);
                flightDurationDataSampling.add(flightTime);
            }

            // calculate percentile
            int flightDurationPercentile = flightTimeCalculator.calculatePercentileInMinutes(flightDurationDataSampling, percentile);
            int flightDurationPercentileHours = (int) Math.floor(flightDurationPercentile / (double) QUANTITY_OF_MINUTES_IN_HOUR);
            int flightDurationPercentileMinutes = flightDurationPercentile - flightDurationPercentileHours * QUANTITY_OF_MINUTES_IN_HOUR;

            // calculate average
            int flightDurationAverage = (int) Math.ceil(flightTimeCalculator.calculateAverageInMinutes(flightDurationDataSampling));
            int flightDurationAverageHours = (int) Math.floor(flightDurationAverage / (double) QUANTITY_OF_MINUTES_IN_HOUR);
            int flightDurationAverageMinutes = flightDurationAverage - flightDurationAverageHours * QUANTITY_OF_MINUTES_IN_HOUR;

            // print results
            String result = String.format("""
                            Результаты вычислений:
                            %d процентиль времени полета между городами: %d часов %d минут
                            Среднее время полета между городами: %d часов %d минут
                            """,
                    percentile, flightDurationPercentileHours, flightDurationPercentileMinutes,
                    flightDurationAverageHours, flightDurationAverageMinutes);
            System.out.print(result);
            writeResultInTxtFile(result);
        } else {
            throw new NullPointerException("Json file was converted to null string value");
        }
    }

    public static String readStringFromJson(String fileName) {
        String json;
        StringBuilder sb = new StringBuilder();

        try (BOMInputStream bomIn = new BOMInputStream(new FileInputStream(fileName), ByteOrderMark.UTF_8);
             BufferedReader br = new BufferedReader(new InputStreamReader(bomIn))) {
            while ((json = br.readLine()) != null) {
                sb.append(json);
            }
            return sb.toString();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public static List<Ticket> checkFlightRoute(List<Ticket> ticketsList, String departureCity, String arrivalCity) {
        return ticketsList.stream()
                .filter(ticket -> ticket.getOriginName().equals(departureCity) && ticket.getDestinationName().equals(arrivalCity))
                .collect(Collectors.toList());
    }

    public static List<Ticket> convertTicketDraftListToTicketList(List<TicketDraft> draftTicketsList) {
        List<Ticket> tickets = new ArrayList<>();
        for (TicketDraft ticketDraft : draftTicketsList) {
            tickets.add(new TicketBuilder()
                    .setOrigin(ticketDraft.getOrigin())
                    .setOriginName(ticketDraft.getOriginName())
                    .setDestination(ticketDraft.getDestination())
                    .setDestinationName(ticketDraft.getDestinationName())
                    .setDepartureDate(ticketDraft.getDepartureDate())
                    .setDepartureTime(ticketDraft.getDepartureTime())
                    .setArrivalDate(ticketDraft.getArrivalDate())
                    .setArrivalTime(ticketDraft.getArrivalTime())
                    .setCarrier(ticketDraft.getCarrier())
                    .setStops(ticketDraft.getStops())
                    .setPrice(ticketDraft.getPrice())
                    .build());
        }
        return tickets;
    }

    public static void writeResultInTxtFile(String result) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        String currentTime = simpleDateFormat.format(Calendar.getInstance().getTime());
        try (FileWriter fileWriter = new FileWriter("travel_app_result_" + currentTime + ".txt")) {
            fileWriter.write(result);
            fileWriter.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}






