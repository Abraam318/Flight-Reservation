import java.io.*;
import java.util.ArrayList;

public class Flight {
    private String Airlines;
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    private String departureTime;
    private String arrivalTime;
    private float Price;
    private int availableSeats;

    public static ArrayList<Flight> flights;
    private static final String FILE_NAME = "flights.txt";

    public Flight(String Airlines, String flightNumber, String departureAirport, String arrivalAirport,
                  String departureTime, String arrivalTime, int availableSeats, float Price) {
        this.Airlines = Airlines;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.Price = Price;
    }

    public String getAirlines() {
        return Airlines;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setAirlines(String airlines) {
        Airlines = airlines;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public float getPrice() {
        return Price;
    }

    public Flight() {
        flights = new ArrayList<>();
        if (flights.isEmpty()) {
            flights.add(new Flight("EGYPTAIR", "FL001", "Alexandria", "Luxor", "08:00", "12:00", 100, 4900F));
            flights.add(new Flight("AIRCAIRO", "FL002", "Cairo", "Alexandria", "14:00", "18:00", 75, 4500F));
            flights.add(new Flight("ALMASRIA", "FL003", "Cairo", "Roma", "10:30", "14:30", 20, 5000F));
            flights.add(new Flight("NILEAIR", "FL004", "Saudia", "Cairo", "10:30", "14:30", 60, 5800F));
        }
        Flight.loadFlightsFromFile();

    }

    public static void loadFlightsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Flight currentFlight = null;

            while ((line = reader.readLine()) != null) {
                if (line.equals("------------------------")) {
                    if (currentFlight != null) {
                        flights.add(currentFlight);
                    }
                    //currentFlight = new Flight();
                    continue;
                }

                String[] parts = line.split(": ");
                if (parts.length == 2) {
                    String field = parts[0];
                    String value = parts[1].trim();

                    if (currentFlight != null) {
                        switch (field) {
                            case "Airlines":
                                currentFlight.setAirlines(value);
                                break;
                            case "Flight Number":
                                currentFlight.setFlightNumber(value);
                                break;
                            case "Departure Airport":
                                currentFlight.setDepartureAirport(value);
                                break;
                            case "Arrival Airport":
                                currentFlight.setArrivalAirport(value);
                                break;
                            case "Departure Time":
                                currentFlight.setDepartureTime(value);
                                break;
                            case "Arrival Time":
                                currentFlight.setArrivalTime(value);
                                break;
                            case "Available Seats":
                                    currentFlight.setAvailableSeats(Integer.parseInt(value));
                                break;
                            case "Price":
                                    currentFlight.setPrice(Float.parseFloat(value));
                                break;
                        }
                    }
                }
            }

            if (currentFlight != null) {
                flights.add(currentFlight);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void saveFlightsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Flight flight : flights) {
                writer.println("Airlines: " + flight.getAirlines());
                writer.println("Flight Number: " + flight.getFlightNumber());
                writer.println("Departure Airport: " + flight.getDepartureAirport());
                writer.println("Arrival Airport: " + flight.getArrivalAirport());
                writer.println("Departure Time: " + flight.getDepartureTime());
                writer.println("Arrival Time: " + flight.getArrivalTime());
                writer.println("Available Seats: " + flight.getAvailableSeats());
                writer.println("Price: " + flight.getPrice());
                writer.println("------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Flight> getFlights() {
        return flights;
    }

    public static void setFlights(Flight flight) {
        flights.add(flight);
    }

    public static void searchFlights(String departureAirport, String arrivalAirport) {
        System.out.println("Searching for flights from " + departureAirport + " to " + arrivalAirport + "...");
        for (Flight flight : flights) {
            if (flight.getDepartureAirport().equalsIgnoreCase(departureAirport) &&
                    flight.getArrivalAirport().equalsIgnoreCase(arrivalAirport)) {
                FlightSelection(flight);
            } else if (flight.getDepartureAirport().equalsIgnoreCase(departureAirport) ||
                    flight.getArrivalAirport().equalsIgnoreCase(arrivalAirport)) {
                FlightSelection(flight);
            }
        }
    }

    private static void FlightSelection(Flight flight) {
        System.out.println("Airline: " + flight.getAirlines());
        System.out.println("Price: " + flight.getPrice());
        System.out.println("Flight Number: " + flight.getFlightNumber());
        System.out.println("Departure Airport: " + flight.getDepartureAirport());
        System.out.println("Arrival Airport: " + flight.getArrivalAirport());
        System.out.println("Departure Time: " + flight.getDepartureTime());
        System.out.println("Arrival Time: " + flight.getArrivalTime());
        System.out.println("Available Seats: " + flight.getAvailableSeats());
        System.out.println("------------------------");
    }
}
