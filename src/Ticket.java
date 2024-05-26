
public class Ticket {

    private static int number_of_tickets = 1;
    private static int nextTicketID = 0;
    static Ticket [] tickets = new Ticket[50];
    public int ticket_number;
    private float base_fare;
    private float fees;
    private float taxes;
    public float ticket_price;
    private Booking booking;
    private String ticket_status;// confirmed, on hold or cancelled

    public void setTicket_status(String ticket_status) {
        this.ticket_status = ticket_status;
    }

    public void setBase_fare(float base_fare) {
        this.base_fare = base_fare;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setFees(float fees) {
        this.fees = fees;
    }

    public void setTaxes(float taxes) {
        this.taxes = taxes;
    }


    public String getTicket_status() {
        return ticket_status;
    }

    public Booking getBooking() {
        return booking;
    }

    public Ticket(float base_fare, float fees, float taxes, Booking booking) {
        if (nextTicketID < tickets.length) {
            this.ticket_number = number_of_tickets++;
            this.base_fare = base_fare;
            this.fees = fees;
            this.taxes = taxes;
            this.booking = booking;
            this.ticket_status = "On Hold";
            tickets[nextTicketID++] = this;
        }else {
            throw new RuntimeException("ticket array is full");
        }


    }


    float calcTicketPrice() {
        ticket_price = (base_fare + fees + taxes);
        return ticket_price;
    }
}