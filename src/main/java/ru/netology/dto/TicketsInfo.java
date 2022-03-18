package ru.netology.dto;

import java.util.List;

public class TicketsInfo {
    protected List<TicketDraft> tickets;

    public TicketsInfo() {

    }

    public TicketsInfo(List<TicketDraft> tickets) {
        this.tickets = tickets;
    }

    public List<TicketDraft> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketDraft> tickets) {
        this.tickets = tickets;
    }
}

