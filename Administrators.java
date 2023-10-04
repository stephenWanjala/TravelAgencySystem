import java.util.ArrayList;
import java.util.List;

public class Administrators {
    private List<Administrator> administrators;

    public Administrators() {
        administrators = new ArrayList<>();
    }

    public void addUser(Administrator admin) {
        administrators.add(admin);
    }

    public boolean isValidCredentials(String username, String password) {
        for (Administrator admin : administrators) {
            if (admin.getEmail().equals(username) && admin.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}



