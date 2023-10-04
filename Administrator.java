import java.util.LinkedList;

public class Administrator {
    private String name;
    private String email;
    private String password;

    public Administrator(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getter methods for name, email, and password
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}



