package Model;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/
public class Customer {


    private String username;
    private String realName;
    private String password;
    private String email;
    private String phoneNumber;

    public Customer(String username, String realName, String password, String email, String phoneNumber) {
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return realName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean verifyPassword(String input) {
        return password.equals(input);
    }

}
