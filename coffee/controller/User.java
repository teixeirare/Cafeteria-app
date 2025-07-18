package coffee.controller;

public class User {
    private String username;
    private String password; // You can use char[] if you want to be more secure

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

