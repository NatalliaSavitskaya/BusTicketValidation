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
    protected String ticketClass;
    protected String ticketType; // DAY, WEEK, YEAR, MONTH.
    protected String startDate; // can't be in the future. For [DAY, WEEK, YEAR] ticketTypes shouldn't be empty
    protected String price; // should always be even

    // constructor
    public BusTicket() {
    }
}
