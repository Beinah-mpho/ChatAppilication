package chatapplication;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ChatApplication {
    // User details
    public String username;
    public String password;
    public String firstName;
    public String lastName;

    // Default constructor
    public ChatApplication() {
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

            boolean loginSuccess = chatApp.loginUser(enteredUsername, enteredPassword);
            System.out.println(chatApp.returnLoginStatus(loginSuccess));
        }

        scanner.close();
    }

    // === Validation Methods ===

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

    // === Registration Process ===
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
            return "Username is not correctly formatted. It must contain an underscore and be no more than five characters.";
        }
        this.username = username;

        // Password validation
        System.out.print("Enter password (>=8 chars, with capital, number, special char): ");
        String password = scanner.nextLine();
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted. It must be at least 8 characters long, include a capital letter, number, and special character.";
        }
        this.password = password;

        // Cell number validation
        System.out.print("Enter cell phone number (with international code, e.g., +27831234567): ");
        String cellNumber = scanner.nextLine();
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number is incorrectly formatted or missing international code.";
        }

        return "Registration successful!";
    }

    // === Login Process ===
    private boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername == null || enteredPassword == null) return false;
        return enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
    }

    // === Login Status Message ===
    public String returnLoginStatus(boolean isSuccessful) {
        if (isSuccessful) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
