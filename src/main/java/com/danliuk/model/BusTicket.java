package com.danliuk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Getter
@Setter
@ToString

public class BusTicket {
    private String ticketClass;
    private String ticketType; // DAY, WEEK, YEAR, MONTH.
    private String startDate; // can't be in the future. For [DAY, WEEK, YEAR] ticketTypes shouldn't be empty
    private String price; // should always be even

    // constructor
    public BusTicket() { }

        public boolean isValidTicketType() {
        return "DAY".equals(ticketType) || "WEEK".equals(ticketType) || "YEAR".equals(ticketType) || "MONTH".equals(ticketType);
    }

    public boolean isValidStartDate() {
       if ((startDate == null)||(startDate.isEmpty()))
           return !"DAY".equals(ticketType) && !"WEEK".equals(ticketType) && !"YEAR".equals(ticketType);
       else {
           LocalDate date = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
           return (!date.isAfter(LocalDate.now()));
       }
    }

    public boolean isValidPrice() {
        if ((price == null)||(price.isEmpty())) return false;
        else
            return (Double.parseDouble(price) % 2 == 0.0);
    }
}
