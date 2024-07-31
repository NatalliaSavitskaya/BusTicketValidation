package com.danliuk;

import com.danliuk.model.BusTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        int counterValidTickets = 0;
        int counterValidPrice = 0;
        int counterValidStartDate = 0;
        int counterTotalTickets = 5;
        int x = 0;

        do {
            String input = getInput();
            BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);

            // Bus Ticket validation
               if (busTicket.isValidPrice())
                   counterValidPrice++;
               else  System.out.println("Invalid price.");
               if (busTicket.isValidStartDate())
                   counterValidStartDate++;
               else  System.out.println("Invalid start date of specific ticket types.");
               if (busTicket.isValidPrice() && busTicket.isValidStartDate())
                   counterValidTickets++;

            System.out.println(busTicket.toString());
            x++;

        } while (x < counterTotalTickets);

        System.out.println("Total = " + x++);
        System.out.println("Valid = " + counterValidTickets++);
        if (counterValidPrice > counterValidStartDate)
            System.out.println("Most popular violation = price.");
        else if (counterValidPrice < counterValidStartDate)
                System.out.println("Most popular violation = start date.");
        else System.out.println("Most popular violation = start date and price.");
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }
}