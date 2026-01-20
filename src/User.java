public class User {
    private final String username;
    private final String address;
    private final String email;
    private final String phone;

    public User(String username, String address, String email, String phone) {
        this.username = username;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }
}
