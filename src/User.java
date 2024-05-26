public abstract class User {
    protected String email;
    protected String password;

    public abstract boolean login(String email, String password) ;
}
