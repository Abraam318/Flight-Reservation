
public class Seat {
    int number;
    public enum Seat_Classes
    {
        economy, business, first_class
    }

    public enum Availability_status {
        window, aisle, exit;
    }

    Seat_Classes Class ;

    Availability_status status;

    public Seat(int number, Seat_Classes Class,Availability_status status)
    {
        //I didn't figure out this part whether should be put here (in this class or not)
        //if(Class != Seat_Classes.economy && status == Availability_status.exit)
        //{
        //    System.out.println("\nThe chosen class doesn't have this section of seats.\nPlease Enter valid Class and Status for the current seat :");
        //    System.out.print("Class : ");
        //    System.out.print("\nStatus : ");
        //}
        this.number = number;
        this.Class = Class;
        this.status = status;
    }

    public int get_seat_number()
    {
        return number;
    }

    public Seat_Classes get_seat_class()
    {
        return Class;
    }

    public Availability_status get_seat_status()
    {
        return status;
    }
}