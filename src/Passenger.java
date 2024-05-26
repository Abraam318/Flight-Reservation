import java.io.*;

public class Passenger extends User {
    public static int id;
    private String name;
    private int phone;
    private static final String FILE_NAME = "Passengers.txt";

    public Passenger() {
    }

    public Passenger(String name, int id, String email, int phone, String password) {
        this.name = name;
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getPhone() {
        return phone;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void savePassenger(String name, int id, String email, int phone, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println("Name: " + name);
            writer.println("ID: " + id);
            writer.println("Email: " + email);
            writer.println("Phone: " + phone);
            writer.println("Password: " + password);
            writer.println("------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
