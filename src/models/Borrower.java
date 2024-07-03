package models;

public class Borrower {
    private final String username;
    private String name;
    private char[] password;

    public Borrower(String username, String name, char[] password) {
        this.username = username;
        this.name = name;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public char[] getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "{Username='" + username +
                "',Name='" + name +
                "'}\n";
    }
}
