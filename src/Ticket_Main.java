
public class Ticket_Main {

    public void main(String[] args) {

        Passenger p = new Passenger("Ahmed",3, "ahmed@gmail.com",0120 ,"123");
        Flight f = new Flight("Qatar","122", "Cairo", "France" , "10:00PM", "12:00PM", 10, 100);
        Booking b = new Booking(p,f,"A32");
        Ticket t = new Ticket(1000.5F, 20F, 3F, b);
        Payment pay = new Payment(t, "Ahmed Mohamed", "42234", "Visa", 2020F);

        pay.setPaymentPassword("Nothing1234");
        try {
            pay.performPayment();
        } catch (InvalidTransaction exc) {
            System.out.println(exc.getMessage());
        }

        /*try {
            t.getBooking().manageBooking();
        }catch (InvalidBookingOperationException exp) {
            System.out.println(exp.getMessage());
        }*/

        if (t.getBooking().getBookingStatus() ) {
            if (t.getBooking().isConfirmed()) {
                t.setTicket_status("Confirmed");
            }
            else {
                t.setTicket_status("On Hold");
            }
        }
        if (!t.getBooking().getBookingStatus()) {
            t.setTicket_status("Cancelled");
        }


        System.out.println(t.getTicket_status());
        System.out.println(t.getBooking().getBookingStatus());
    }
}