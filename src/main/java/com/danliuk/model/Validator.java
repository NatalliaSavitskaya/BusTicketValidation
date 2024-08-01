package com.danliuk.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validator extends BusTicket{
    public static int counterInvalidTicketType = 0;
    public static int counterInvalidStartDate = 0;
    public static int counterInvalidPrice = 0;
    public static int counterValidTickets = 0;

    public Validator (BusTicket busTicket) {
        this.price = busTicket.price;
        this.startDate = busTicket.startDate;
        this.ticketClass = busTicket.ticketClass;
        this.ticketType = busTicket.ticketType;
    }

    public boolean isValidTicketType() {
        return ("DAY".equals(ticketType) || "WEEK".equals(ticketType)
                || "YEAR".equals(ticketType) || "MONTH".equals(ticketType));
    }

    public boolean isValidStartDate() {
        if ((startDate == null) || (startDate.isEmpty()))
            return (!"DAY".equals(ticketType) && !"WEEK".equals(ticketType) && !"YEAR".equals(ticketType));
        else {
            LocalDate date = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return (!date.isAfter(LocalDate.now()));
        }
    }

    public boolean isValidPrice() {
        if ((price == null) || (price.isEmpty())) return false;
        else
            return (Double.parseDouble(price) % 2 == 0.0);
    }

    public void ticketValidation() {
        if (!this.isValidTicketType()) {
            counterInvalidTicketType++;
            System.out.println("Invalid ticket type.");
        }
        if (!this.isValidStartDate()) {
            counterInvalidStartDate++;
            System.out.println("Invalid start date.");
        }
        if (!this.isValidPrice()) {
            counterInvalidPrice++;
            System.out.println("Invalid price.");
        }
        if (this.isValidTicketType() && this.isValidStartDate() && this.isValidPrice())
            counterValidTickets++;
    }
}
