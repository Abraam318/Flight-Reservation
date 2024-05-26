import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class HomePage {

    private static List<Passenger> passengers = new ArrayList<>();
    private static List<Admin> admins = new ArrayList<>();
    private static boolean justSignedUp = false;

    public static void main(String[] args) {
        passengers.add(new Passenger("a", 1, "123", 12345, "123"));
        admins.add(new Admin("admin", "P@$S123"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Flight Booking System!");
        System.out.println("--------------------------------------");

        while (true) {
            User loggedInUser = loginOrSignUp(scanner);

            if (loggedInUser != null) {
                if (loggedInUser instanceof Passenger) {
                    displayPassengerMenu(scanner, (Passenger) loggedInUser);


                } else if (loggedInUser instanceof Admin) {
                    displayAdminMenu(scanner, (Admin) loggedInUser);
                }
            }

            if (justSignedUp) {
                justSignedUp = false;
                continue;
            }

            System.out.print("Do you want to try again? (y/n): ");
            String tryAgain = scanner.nextLine().toLowerCase();

            if (!tryAgain.equals("y")) {
                Flight s = new Flight();
                System.out.println("Exiting the Flight System. Goodbye!");
                break;
            }
        }

        scanner.close();
    }

    private static User loginOrSignUp(Scanner scanner) {
        try {
            System.out.println("1. Login");
            System.out.println("2. Login as admin");
            System.out.println("3. Sign Up");

            System.out.print("Enter the number of your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    return login(scanner);
                case 2:
                    return loginAsAdmin(scanner);
                case 3:
                    User newUser = signUp(scanner);
                    if (newUser != null) {
                        System.out.println("Sign-up successful. You can now log in.");
                        justSignedUp = true;
                        return login(scanner);
                    }
                    return null;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
                    return null;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return null;
        }
    }

    private static User login(Scanner scanner) {
        try {
            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            for (Passenger passenger : passengers) {
                if (passenger.login(email, password)) {
                    System.out.println("Login successful.");
                    return passenger;
                }
            }

            System.out.print("Invalid credentials. Do you want to try again? (y/n): ");
            String tryAgain = scanner.nextLine().toLowerCase();

            if (!tryAgain.equals("y")) {
                System.out.println("Returning to the main menu.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
        return null;
    }

    private static Admin loginAsAdmin(Scanner scanner) {

        System.out.print("Enter your admin email: ");
        String email = scanner.nextLine();

        System.out.print("Enter your admin password: ");
        String password = scanner.nextLine();
        try {

            for (Admin admin : admins) {
                if (admin.login(email, password)) {
                    System.out.println("Admin login successful.");
                    return admin;
                }
            }

            System.out.print("Invalid credentials. Do you want to try again? (y/n): ");
            String tryAgain = scanner.nextLine().toLowerCase();

            if (!tryAgain.equals("y")) {
                System.out.println("Returning to the main menu.");
                return null;
            }
        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
        return null;
    }


    private static User signUp(Scanner scanner) {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine();

            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            System.out.print("Enter your phone number: ");
            int phone = scanner.nextInt();
            scanner.nextLine();
            Passenger a = new Passenger();
            a.id++;

            passengers.add(new Passenger(name, a.id,email,phone, password));
            System.out.println("User Added Successfully: ");
            a.savePassenger(name, a.id, email, phone, password);
            return passengers.get(passengers.size() - 1); // Return the last added user

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.nextLine();
            return null;
        }
    }

    private static void displayPassengerMenu(Scanner scanner, Passenger passenger) {
        try {
            while (true) {
                System.out.println("\nPassenger Menu:");
                System.out.println("1. Search Flights");
                System.out.println("2. View Bookings");
                System.out.println("3. Book a Flight");
                System.out.println("4. Confirm a Flight");
                System.out.println("5. Manage your booking");
                System.out.println("6. Logout");

                System.out.print("Enter the number of your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        searchFlights(scanner);
                        break;
                    case 2:
                        viewBookings(passenger);
                        break;
                    case 3:
                        bookFlight(scanner, passenger);
                        break;
                    case 4:
                        ConfirmAFlight(scanner, passenger);
                        break;
                    case 5:
                        ManageYourBooking(scanner,passenger);
                        break;
                    case 6:
                        System.out.println("Logging out.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void displayAdminMenu(Scanner scanner, Admin admin) {
        try {
            while (true) {
                System.out.println("\nAdmin Menu:");
                System.out.println("1. View All Bookings");
                System.out.println("2. Cancel a Flight");
                System.out.println("3. Add a Flight");
                System.out.println("4. Modify a Flight");
                System.out.println("5. Logout");

                System.out.print("Enter the number of your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewAllBookings();
                        break;
                    case 2:
                        cancelFlight(scanner);
                        break;
                    case 3:
                        AddFlight(scanner);
                        break;
                    case 4:
                        ModifyFlight(scanner);
                        break;
                    case 5:
                        System.out.println("Logging out.");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void ModifyFlight(Scanner scanner) {

        boolean isFound = false;
        String flightNum;
        System.out.println("Enter flight number: ");
        flightNum = scanner.nextLine();
        scanner.nextLine();

        ArrayList <Flight> flights = Flight.getFlights();



        for (Flight flight:flights) {

            if (flight != null) {
                if (flight.getFlightNumber().equals(flightNum)) {

                    isFound = true;
                    System.out.println("1.Modify Airlines");
                    System.out.println("2.Modify Departure Airport");
                    System.out.println("3.Modify Arrival Airport");
                    System.out.println("4.Modify Available Seats");
                    System.out.println("5.Modify Arrival time");
                    System.out.println("6.Modify Departure time");

                    System.out.println("Enter your choice: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    switch (choice) {

                        case 1:
                            System.out.println("Enter airline name: ");
                            String airline = scanner.nextLine();
                            flight.setAirlines(airline);
                            break;
                        case 2:
                            System.out.println("Enter departure airport: ");
                            String departure_airport = scanner.nextLine();
                            flight.setDepartureAirport(departure_airport);
                            break;
                        case 3:
                            System.out.println("Enter arrival airport: ");
                            String arrival_airport = scanner.nextLine();
                            flight.setArrivalAirport(arrival_airport);
                            break;
                        case 4:
                            System.out.println("Enter number of available seats: ");
                            int available_seats = scanner.nextInt();
                            flight.setAvailableSeats(available_seats);
                            break;
                        case 5:
                            System.out.println("Enter arrival time: ");
                            String ArrivalTime = scanner.nextLine();
                            flight.setArrivalTime(ArrivalTime);
                            break;
                        case 6:
                            System.out.println("Enter departure time: ");
                            String DepartureTime = scanner.nextLine();
                            flight.setArrivalTime(DepartureTime);
                            break;
                        default:
                            System.out.println("invalid input");
                    }
                    break;
                }

            }

        }

        if (!isFound) {
            System.out.println("Wrong flight ID.");
        }

    }
    private static void AddFlight(Scanner scanner) {
        String airlineName;
        System.out.println("Enter the name of the airlines: ");
        airlineName = scanner.nextLine();

        String flightNumber;
        System.out.println("Enter flight number: ");
        flightNumber = scanner.nextLine();

        String departureAirport;
        System.out.println("Enter departure airport name: ");
        departureAirport = scanner.nextLine();

        String arrivalAirport;
        System.out.println("Enter arrival airport name: ");
        arrivalAirport = scanner.nextLine();

        String departureTime;
        System.out.println("Enter departure time: ");
        departureTime = scanner.nextLine();

        String arrivalTime;
        System.out.println("Enter arrival time: ");
        arrivalTime = scanner.nextLine();

        int availableSeats;
        System.out.println("Enter available seats: ");
        availableSeats = Integer.parseInt(scanner.nextLine());

        float flightPrice;
        System.out.println("Enter flight price: ");
        flightPrice = Float.parseFloat(scanner.nextLine());


        Flight f = new Flight();
        Flight.flights.add(new Flight(airlineName, flightNumber, departureAirport, arrivalAirport, departureTime, arrivalTime, availableSeats, flightPrice));

        if (Flight.getFlights() != null) {
            System.out.println("Flight number: " + flightNumber + " added");
        }
        Flight.saveFlightsToFile();
    }


    private static void searchFlights(Scanner scanner) {
        try {
            System.out.println("\nSearch Flights:");
            System.out.print("Enter departure airport: ");
            String departureAirport = scanner.nextLine();

            System.out.print("Enter arrival airport: ");
            String arrivalAirport = scanner.nextLine();

            Flight.searchFlights(departureAirport, arrivalAirport);

        } catch (Exception e) {
            System.out.println("An error occurred. Please try again.");
        }
    }

    private static void viewBookings(Passenger passenger) {
        System.out.println("\nView Bookings:");
        boolean isPrint = false;

        for (Ticket ticket:Ticket.tickets) {

            if (ticket != null) {
                if (ticket.getBooking().getPassenger() == passenger) {
                    System.out.println(ticket.getBooking().toString());
                    System.out.println(ticket.getTicket_status());
                    System.out.println("=================================");
                    isPrint = true;
                }

            }
        }

        if (!isPrint) {
            System.out.println("There are no bookings");
        }
    }

    private static void viewAllBookings() {
        boolean isThereBookings = false;
        System.out.println("\nView Bookings:");

        for (Ticket ticket:Ticket.tickets) {

            if (ticket != null) {
                System.out.println(ticket.getBooking().toString());
                System.out.println("ticket status: " + ticket.getTicket_status());
                System.out.println("=================================");
                isThereBookings = true;
            }
        }

        if (!isThereBookings) {
            System.out.println("There are no bookings.");
        }

    }

    //modification
    // check if the flight number is in the flight list do the booking
    private static ArrayList <Ticket> ticketsNoPay = new ArrayList<>();
    private static ArrayList <Payment> NullPayments = new ArrayList<>();
    static int index = 0, value = 1;
    private static void bookFlight(Scanner scanner, Passenger passenger) {
        boolean flag = false;
        String choise2;

        System.out.println("\nEnter the number of the flight: ");
        String flightNum = scanner.nextLine();

        ArrayList<Flight> allFlights = Flight.getFlights();

        for (Flight flight1:allFlights) {
            if (flight1.getFlightNumber().equals(flightNum)) {
                Booking.bookings[index] = new Booking(passenger, flight1, "A"+ value);
                Ticket.tickets[index] = new Ticket(1000F, 250F, 25.5F, Booking.bookings[index]);
                System.out.println("Booking completed.");
                flag = true;

                // pay now or later || create ticket
                System.out.println("Pay now ? (Y, N)");
                choise2 = scanner.nextLine().toLowerCase();

                switch (choise2) {
                    case "y":
                        String input;
                        Payment.payments[index] = new Payment(Ticket.tickets[index],passenger.getName(), "41441","Visa",10000);
                        System.out.println("Enter the payment password that will save in the application: ");
                        input = scanner.nextLine();
                        Payment.payments[index].setPaymentPassword(input);
                        try {
                            Payment.payments[index].performPayment();
                        }catch (InvalidTransaction exp) {
                            System.out.println(exp.getMessage());
                        }

                        break;
                    case "n":
                        System.out.println("Please confirm later.");
                        ticketsNoPay.add(Ticket.tickets[index]);
                        Payment.payments[index] = new Payment(Ticket.tickets[index],passenger.getName(), "41441","Visa",10000);

                        System.out.println("Enter the payment password that will save in the application: ");
                        input = scanner.nextLine();
                        Payment.payments[index].setPaymentPassword(input);
                        NullPayments.add(Payment.payments[index]);

                        break;
                    default:
                        throw new InputMismatchException("Invalid input.");
                }
                value++;
                index++;
                break;
            }
        }

        if (!flag) {
            System.out.println("You entered wrong flight number.");
        }


    }

    private static void ManageYourBooking(Scanner scanner,Passenger passenger) {
        int myInput;
        boolean isThereBooking = false;
        viewBookings(passenger);

        System.out.println("Enter the ID of booking you want to manage: ");
        myInput = scanner.nextInt();

        for (Booking booking:Booking.bookings) {

            if (booking != null) {

                if (booking.getPassenger() == passenger && booking.getBookingId() == myInput) {

                    try {
                        booking.manageBooking();
                    } catch (Booking.InvalidBookingOperationException exp) {
                        System.out.println(exp.getMessage());
                    }
                    isThereBooking = true;
                    break;
                }
            }

        }

        if (!isThereBooking) {
            System.out.println("There is no booking for this user to manage.");
        }
    }

    private static void ConfirmAFlight(Scanner scanner, Passenger passenger) {
        boolean isFound = false;
        boolean wrongPassword = false;
        if (ticketsNoPay.isEmpty()) {
            System.out.println("There are no bookings to confirm");
        }

        for (Ticket t:ticketsNoPay) {
            System.out.println(t.getBooking().toString());
            System.out.println("Ticket status: " + t.getTicket_status());
            System.out.println("=============================");

        }

        int input;
        if (!ticketsNoPay.isEmpty()) {
            System.out.println("Enter booking ID to confirm your booking: ");
            input = scanner.nextInt();

            for (Payment p : NullPayments) {
                if (p.getTicket().getBooking().getBookingId() == input && !p.getTicket().getBooking().isConfirmed() && !p.getTicket().getBooking().isCancelled()) {
                    isFound = true;
                    String input2;

                    try {
                        p.performPayment();
                    } catch (InvalidTransaction exp) {
                        wrongPassword = true;
                        System.out.println(exp.getMessage());
                    }

                    if (!wrongPassword) {
                        ticketsNoPay.remove(p.getTicket());
                        NullPayments.remove(p);
                    }
                    break;
                }
            }

            if (!isFound) {
                System.out.println("Booking not found");
            }
        }

    }

    private static void cancelFlight(Scanner scanner) {

        System.out.println("\nCancel a Flight:");

        String flightNum;
        System.out.println("Enter flight number");
        flightNum = scanner.nextLine();

        ArrayList <Flight> flights = Flight.getFlights();


        for (Flight ourFlights:flights) {

            if (ourFlights != null) {
                if (ourFlights.getFlightNumber().equals(flightNum)) {
                    flights.remove(ourFlights);
                    System.out.println("Flight number: " + ourFlights.getFlightNumber() + " removed");
                    break;
                }
            }

        }
    }
}
