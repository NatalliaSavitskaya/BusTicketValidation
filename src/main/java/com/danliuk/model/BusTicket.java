package com.danliuk.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class BusTicket {
    private String ticketClass;
    private String ticketType;
    private String startDate; // DAY, WEEK, YEAR
    private String price; // !=zero

    // constructors
    public BusTicket() { }

    public boolean isValidPrice() {
        if (price == null) return false;
        else
            return (Double.parseDouble(price) != 0.0);
    }

    public boolean isValidStartDate() {
        if ("DAY".equals(ticketType) || "WEEK".equals(ticketType) || "YEAR".equals(ticketType))
            return (startDate != null)&&(!startDate.isEmpty());
        else return true;
    }
}
