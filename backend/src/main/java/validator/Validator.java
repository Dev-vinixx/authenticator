package validator;

import org.jetbrains.annotations.NotNull;

public class Validator {

    // Validates the provided name
    public boolean validateName(@NotNull String name) throws NullPointerException, IllegalArgumentException {
        if (name.isBlank()) {
            throw new NullPointerException("The 'name' field cannot be left blank.");
        } else if (name.matches(".*[^a-zA-Z].*")) {
            throw new IllegalArgumentException("The 'name' field cannot contain special characters.");
        } else if (!name.matches("(\\p{Lu}\\p{Ll}*)*")) {
            throw new IllegalArgumentException("Invalid name format.");
        }
        return true;
    }

    // Validates the provided phone number
    public boolean validateNumber(@NotNull String phone) throws NullPointerException, IllegalArgumentException {
        if (phone.isBlank()) {
            throw new NullPointerException("The 'number' field cannot be left blank.");
        } else if (!phone.matches("[^a-zA-Z]*")) {
            throw new IllegalArgumentException("The phone number field should only contain digits.");
        } else if (!phone.matches("\\+\\d{2} \\(\\d{2}\\) \\d{4}-\\d{4}")) {
            throw new IllegalArgumentException("Invalid phone number format.");
        }
        return true;
    }

    // Validates the provided email
    public boolean validateEmail(@NotNull String email) throws NullPointerException, IllegalArgumentException {
        if (email.isBlank()) {
            throw new NullPointerException("The 'email' field cannot be left blank.");
        } if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        String[] parts = email.split("@");

        if (!parts[0].matches("^[a-zA-Z0-9._-]+$")) {
            throw new IllegalArgumentException("The 'email' field cannot contain special characters.");
        } else if (!parts[1].matches("^[a-zA-Z.]+$")) {
            throw new IllegalArgumentException("Invalid domain format.");
        }

        return true;
    }
}