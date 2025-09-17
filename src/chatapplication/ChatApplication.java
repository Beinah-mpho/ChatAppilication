package chatapplication;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ChatApplication {
    // User details
    public String username;
    public String password;
    public String firstName;
    public String lastName;

    public ChatApplication() {
    }

    public ChatApplication(String firstName, String lastName, String username, String password) {
        if (firstName == null || lastName == null || username == null || password == null) {
            throw new IllegalArgumentException("Constructor arguments cannot be null.");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    // Main method 
    public static void main(String[] args) {
        ChatApplication chatApp = new ChatApplication();
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Registration ===");
        String registrationResult = chatApp.registerUser(scanner);
        System.out.println(registrationResult);

        if (registrationResult.toLowerCase().contains("successful")) {
            System.out.println("\n=== Login ===");
            System.out.print("Enter username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter password: ");
            String enteredPassword = scanner.nextLine();

            // Use Login class
            Login login = new Login(chatApp.username, chatApp.password, chatApp.firstName, chatApp.lastName);
            boolean loginSuccess = login.loginUser(enteredUsername, enteredPassword);
            System.out.println(login.returnLoginStatus(loginSuccess));
        }

        scanner.close();
    }

    // Username validation 
    private boolean checkUsername(String username) {
        if (username == null) return false;
        return username.length() <= 5 && username.contains("_");
    }

    // Password complexity check 
    private boolean checkPasswordComplexity(String password) {
        if (password == null) return false;
        if (password.length() < 8) return false;
        if (!Pattern.compile("[A-Z]").matcher(password).find()) return false;
        if (!Pattern.compile("[0-9]").matcher(password).find()) return false;
        return Pattern.compile("[^A-Za-z0-9]").matcher(password).find();
    }

    // Cell phone number format check 
    private boolean checkCellPhoneNumber(String cellNumber) {
        if (cellNumber == null) return false;
        String pattern = "^\\+\\d{1,3}\\d{7,10}$";
        return Pattern.matches(pattern, cellNumber);
    }

    // User registration process
    private String registerUser(Scanner scanner) {
        if (scanner == null) return "Scanner is not available.";

        System.out.print("Enter your first name: ");
        this.firstName = scanner.nextLine();
        if (this.firstName == null || this.firstName.isBlank()) return "First name cannot be empty.";

        System.out.print("Enter your last name: ");
        this.lastName = scanner.nextLine();
        if (this.lastName == null || this.lastName.isBlank()) return "Last name cannot be empty.";

        // Username validation
        System.out.print("Enter username (must contain _ and be <=5 characters): ");
        String username = scanner.nextLine();
        if (!checkUsername(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        this.username = username;

        // Password validation
        System.out.print("Enter password (>=8 chars, with capital, number, special char): ");
        String password = scanner.nextLine();
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        this.password = password;

        // Cell number validation
        System.out.print("Enter cell phone number (with international code, e.g., +27831234567): ");
        String cellNumber = scanner.nextLine();
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }

        return "Registration successful!";
    }
}


class Login {
    private final String storedUsername;
    private final String storedPassword;
    private final String firstName;
    private final String lastName;

    public Login(String storedUsername, String storedPassword, String firstName, String lastName) {
        this.storedUsername = storedUsername;
        this.storedPassword = storedPassword;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Method to verify login credentials
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername == null || enteredPassword == null) return false;
        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
    }

    // Method to return login status message
    public String returnLoginStatus(boolean isSuccessful) {
        if (isSuccessful) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
*References(at attribution for ideas)
*ChatGPT,(2025,September 17). Regular-expression pattern guidance for international phone formarts with country codes.
*OpenAI, " Add oop concepts and null guards then create a method to verify log in gredentials "
