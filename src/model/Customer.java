package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstname;
    private String lastName;
    private String email;

    private static final String emailRegex = "^(.+)@(.+).com$";
    private static final Pattern pattern = Pattern.compile(emailRegex);

    // TODO write Customer constructor

    public Customer(String firstname, String lastName, String email) {
        this.firstname = firstname;
        this.lastName = lastName;
        validateEmail(email);
    }

    // TODO double-check Customer.validateEmail is fits with project spec
    public void validateEmail(String email) {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, invalid email");
        } else {
            setEmail(email);
        }
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "First Name: " + firstname + " " +
                "Last Name: " + lastName + " " +
                "Email: " + email;
    }
}
