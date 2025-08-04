package validator;

import java.util.*;
import java.util.regex.Pattern;

public class Validator {
	
	private static final Pattern VALID_USERNAME_PATTERN =
			Pattern.compile("^[A-Za-z]+$");
    
    private static final Pattern VALID_PASSWORD_PATTERN =
            Pattern.compile("^(?=.*\\d).{4,}$");
    
    public static HashMap<String, String> validSignUp(String username, String password, String passwordRetype) {
        HashMap<String, String> errors = new HashMap<>();
        
        if ( username.trim().isEmpty() || password.trim().isEmpty() ||
            passwordRetype.trim().isEmpty()) {
                
        	errors.put("global", "Please complete all required fields.");
                return errors; 
            }
        
        if (username.trim().length() < 3 || username.trim().length() > 50) {
            errors.put("username", "Username must be between 3 and 50 characters long.");
        }

        if (!VALID_USERNAME_PATTERN.matcher(username).matches()) {
            errors.put("username", "Username must contain letters only.");
        }

        if (!VALID_PASSWORD_PATTERN.matcher(password).matches()) {
            errors.put("password", "Password must be at least 4 characters long and include at least one number.");
        }

        if (!password.equals(passwordRetype)) {
            errors.put("retypePassword", "Passwords do not match.");
        }

        return errors;
    }
    
    public static HashMap<String, String> validLogin(String username, String password) {
        HashMap<String, String> errors = new HashMap<>();  
        
        if ( username.trim().isEmpty() || password.trim().isEmpty()) {     
        	errors.put("global", "Please complete all required fields.");
                return errors; 
            }
            
            if (username.trim().length() < 3 || username.trim().length() > 50) {
                errors.put("username", "Username must be between 3 and 50 characters long.");
            }

            if (!VALID_USERNAME_PATTERN.matcher(username).matches()) {
                errors.put("username", "Username must contain letters only.");
            }

            if (!VALID_PASSWORD_PATTERN.matcher(password).matches()) {
                errors.put("password", "Password must be at least 4 characters long and include at least one number.");
            }

        return errors;
    }
  
}


