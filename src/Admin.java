import java.util.List;

public class Admin extends User {

    public Admin(String email, String password) {
        super();
    }

    public void update_flight_airlines(Flight flight, String new_airline) {
        flight.setAirlines(new_airline);
    }
    public void update_flight_number(Flight flight, String new_flightnumber) {
        flight.setFlightNumber(new_flightnumber);
    }
    public void update_DEPARTUREAIRPORT(Flight flight, String new_departureairport) {
        flight.setDepartureAirport(new_departureairport);
    }
    public void update_ArrivalAirport(Flight flight, String new_ArrivalAirport) {
        flight.setArrivalAirport(new_ArrivalAirport);
    }
    public void update_Price(Flight flight, int new_Price) {
        flight.setPrice(new_Price);
    }
    public void update_DepartureTime(Flight flight, String new_DepartureTime) {
        flight.setDepartureTime(new_DepartureTime);
    }
    public void update_ArrivalTime(Flight flight, String new_ArrivalTime) {
        flight.setArrivalTime(new_ArrivalTime);
    }
    public void update_AvailableSeats(Flight flight, int new_ArrivalTime) {
        flight.setAvailableSeats(new_ArrivalTime);
    }

    public void add_new_flight(List<Flight> flights, Flight flight) {
        flights.add(flight);
    }

//public void change_Availability_status(Seat a,Availability_status new_status)
//{
//    a.status=new_status;
//
//}
//
//    public void change_seat_classes(Seat a,Seat_Classes new_class)
//    {
//        a.Class=new_class;
//
//    }
//    public void change_number(Seat a,int new_number)
//    {
//        a.number=new_number;
//
//    }


    @Override
    public boolean login(String email, String password) {
        if (email.equals("admin") && password.equals("admin1234"))
            return true;
        return false;
    }
}
