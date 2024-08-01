package com.danliuk;

import com.danliuk.model.BusTicket;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.Math;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        int x = 0;

        // Choosing the variant of input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 1, if you want to input the Bus Ticket by hand, " +
                "2 - if you want to import Bus Tickets from the file.");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        switch (choice) {
            case 1:

                // Manual input of Bus Tickets
                System.out.println("How many Bus Tickets do you want to enter?.");
                int counterTotalTickets = scanner.nextInt();
                scanner.nextLine(); // Clear the buffer
                if (counterTotalTickets > 0) {
                    System.out.println("Enter the data.");
                    do {
                        String input = getInput();
                        BusTicket busTicket = new ObjectMapper().readValue(input, BusTicket.class);
                        busTicket.ticketValidation(); // Bus Ticket validation
                        System.out.println(busTicket.toString());
                        x++;
                    } while (x < counterTotalTickets);
                } else throw new IllegalArgumentException("The number of tickets can't be <= 0.");
                break;

            case 2:

                // Reading from the file
                File jsonFile = new File("src/main/resources/ticketData.txt");
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(jsonFile))) {
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        try {
                            BusTicket busTicket = new ObjectMapper().readValue(line, BusTicket.class);
                            x++;
                            busTicket.ticketValidation(); // Bus Ticket validation
                            System.out.println(busTicket.toString());
                        } catch (IOException e) {
                            System.err.println("Error parsing JSON: " + e.getMessage());
                        }
                    }
                } catch (IOException e) {
                    System.err.println("Error reading the file: " + e.getMessage());
                }
                break;

            default:
                System.err.println("Wrong choice. Please press 1 or 2.");
                return;
        }
        scanner.close();
        printResult(x);
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    private static void printResult(int x) {
        System.out.println("Total = " + x++);
        System.out.println("Valid = " + BusTicket.counterValidTickets++);
        int max = Math.max(BusTicket.counterInvalidTicketType,
                Math.max(BusTicket.counterInvalidStartDate, BusTicket.counterInvalidPrice));
        if (max == 0)
            System.out.println("There is no invalid tickets.");
        else if (max == BusTicket.counterInvalidTicketType)
            System.out.println("Most popular violation = type.");
        else if (max == BusTicket.counterInvalidStartDate)
            System.out.println("Most popular violation = start date.");
        else System.out.println("Most popular violation = price.");
    }
}