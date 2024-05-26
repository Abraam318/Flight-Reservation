import java.util.Scanner;
class InvalidTransaction extends RuntimeException {
    public InvalidTransaction(String message) {
        super(message);
    }
}
public class Payment {

    /* the owner of the credit card, debit card,
     prepaid card or else */

    private static int nextPaymentID = 0;
    static Payment [] payments = new Payment[50];
    private String ownerName;
    private boolean paymentStats = false;
    private String paymentID;
    private String paymentPassword;
    private String paymentMethod;
    private final float paymentAmount;
    private float balance_in_payment;
    private Ticket ticket;
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public void setBalance_in_payment(float balance_in_payment) {
        this.balance_in_payment = balance_in_payment;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public Ticket getTicket() {
        return ticket;
    }

    public float getBalance_in_payment() {
        return balance_in_payment;
    }

    public float getPaymentAmount() {
        return paymentAmount;
    }

    public void performPayment() throws InvalidTransaction {
        System.out.print("Enter payment password: ");
        Scanner myInput = new Scanner(System.in);
        String password = myInput.nextLine();

        if (this.paymentPassword.equals(password)) {
            if (balance_in_payment < paymentAmount) {
                throw new InvalidTransaction("Invalid transaction. you don't have enough charge");
            } else {
                this.balance_in_payment -= ticket.ticket_price;
                paymentStats = true;
                ticket.getBooking().confirmBooking();
                ticket.setTicket_status("confirmed");
            }
        } else {
            throw new InvalidTransaction("Invalid transaction. wrong password.");
        }
    }


    public Payment(Ticket ticket, String ownerName, String paymentID, String paymentMethod, float balance_in_payment) {

        if (nextPaymentID < payments.length) {
            this.ticket = ticket;
            this.ownerName = ownerName;
            this.paymentID = paymentID;
            this.paymentMethod = paymentMethod;
            this.balance_in_payment = balance_in_payment;
            this.paymentAmount = ticket.calcTicketPrice();
            payments[nextPaymentID++] = this;
        } else {
            throw new InvalidTransaction("Array is full, you can't perform another payment");
        }
    }

}