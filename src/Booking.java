import java.util.*;


// Booking class representing a booking made by a passenger for a specific flight
class Booking {


    private static int nextBookingId = 0;
    static Booking[] bookings = new Booking[50]; // Adjust the size based on your needs
    private int bookingId;
    private Passenger passenger;
    private Flight flight;
    private  String seatNumber;
    private boolean bookingStatus = false;
    private boolean isCancelled = false;
    private boolean isConfirmed = false;


    public Booking(Passenger passenger, Flight flight, String seatNumber) {
        if (nextBookingId < bookings.length){

            this.bookingId = nextBookingId + 1;
            this.passenger = passenger;
            this.flight = flight;
            this.seatNumber = seatNumber;
            this.bookingStatus = false;
            bookings[nextBookingId++] = this; // Add the booking to the array
        }
        else {
            System.out.println("Cannot add a new booking. Array is full.");
        }
    }
    // Getter methods


    public int getBookingId() {
        return bookingId;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public boolean getBookingStatus() {
        return bookingStatus;
    }


    // Method to confirm the booking
    public void confirmBooking() {

        this.isConfirmed = true;
        this.bookingStatus = true;
        System.out.println("Booking confirmed. Details: " + toString());
    }

    // Method to manage the booking (update or cancel)
    public void manageBooking() throws InvalidBookingOperationException {
        if (bookingStatus && isConfirmed) {
            throw new InvalidBookingOperationException("Cannot modify a confirmed booking.");
        }

        // Simulating airline's policies
        boolean allowedByAirline = checkAirlinePolicies();

        if (allowedByAirline) {
            // Implement code to allow users to make changes or cancellations
            System.out.println("Choose an option:");
            System.out.println("1. Modify Seat Number");
            System.out.println("2. Cancel Booking");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    modifySeatNumber();
                    break;
                case 2:
                    cancelBooking();
                    break;
                default:
                    throw new InvalidBookingOperationException("Invalid option selected.");
            }

            System.out.println("Booking modified/cancelled. Details: " + toString());
        } else {
            throw new InvalidBookingOperationException("Changes or cancellations not allowed by airline's policies.");
        }
    }

    private void modifySeatNumber() {
        System.out.println("Enter the new seat number:");
        Scanner scanner = new Scanner(System.in);
        String newSeatNumber = scanner.nextLine();
        this.seatNumber = newSeatNumber;
    }

    private void cancelBooking() {
        int indexToRemove = -1;

        // Find the index of the booking to be canceled
        Booking bookingToRemove = this;  // Store the current booking instance

        for (int i = 0; i < nextBookingId; i++) {
            if (bookings[i] == bookingToRemove) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            // Shift elements to the left to remove the canceled booking
            for (int i = indexToRemove; i < nextBookingId ; i++) {
                bookings[i] = bookings[i + 1];
            }

            nextBookingId--;
            bookingStatus = false;
            isCancelled = true;
        }
    }

    @Override
    public String toString() {
        String word = null;
        if (!isCancelled) {
            if (!isConfirmed) {
                bookingStatus = true;
            }
            word = ", Booking Status: " + (isConfirmed ? "Confirmed" : "Pending");
        }else {
            word = ", Booking Status: Cancelled";
        }

        return "Booking ID: " + bookingId +
                ", Passenger: " + passenger.getName() +
                ", Flight: " + flight.getFlightNumber() +
                ", Departure: " + flight.getDepartureAirport() +
                ", Destination: " + flight.getArrivalAirport() +
                ", Seat Number: " + seatNumber + word;

    }

    private boolean checkAirlinePolicies() {
        // Simulate checking airline policies
        // You can implement more complex logic based on your requirements
        // For simplicity, let's assume changes/cancellations are always allowed
        return true;
    }

    // Custom exception for handling invalid booking operations
    static class InvalidBookingOperationException extends Exception {
        public InvalidBookingOperationException(String message) {
            super(message);
        }
    }
}