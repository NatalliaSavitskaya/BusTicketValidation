package com.danliuk;

import com.danliuk.model.BusTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.Math;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        int counterTotalTickets = 5;
        int counterValidTickets = 0;
        int counterInalidTicketType = 0;
        int counterInalidStartDate = 0;
        int counterInvalidPrice = 0;
        int x = 0;

        do {
            String input = getInput();
            BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);

            // Bus Ticket validation
            if (!busTicket.isValidTicketType()) {
                counterInalidTicketType++;
                System.out.println("Invalid ticket type.");
            }
            if (!busTicket.isValidStartDate()) {
                counterInalidStartDate++;
                System.out.println("Invalid start date.");
            }
            if (!busTicket.isValidPrice()) {
                counterInvalidPrice++;
                System.out.println("Invalid price.");
            }
            if (busTicket.isValidTicketType() && busTicket.isValidStartDate() && busTicket.isValidPrice())
                   counterValidTickets++;

            System.out.println(busTicket.toString());
            x++;

        } while (x < counterTotalTickets);

        System.out.println("Total = " + x++);
        System.out.println("Valid = " + counterValidTickets++);

        int max = Math.max(counterInalidTicketType, Math.max(counterInalidStartDate, counterInvalidPrice));
        if (max == 0)
            System.out.println("There is no invalid tickets.");
        else if (max==counterInalidTicketType)
                 System.out.println("Most popular violation = type.");
             else if (max==counterInalidStartDate)
                     System.out.println("Most popular violation = start date.");
                  else System.out.println("Most popular violation = price.");
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
}