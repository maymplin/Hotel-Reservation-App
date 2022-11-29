package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private final String firstname;
    private final String lastName;
    private String email;

    private static final String emailRegex = "^(.+)@(.+).com$";
    private static final Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstname, String lastName, String email) {
        this.firstname = firstname;
        this.lastName = lastName;
        validateEmail(email);
    }

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

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return getEmail().equals(customer.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }

    @Override
    public String toString() {
        return "First Name: " + firstname + " / " +
                "Last Name: " + lastName + " / " +
                "Email: " + email;
    }
}
