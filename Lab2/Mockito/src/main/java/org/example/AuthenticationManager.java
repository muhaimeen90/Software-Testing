package org.example;

import java.util.HashMap;

public class AuthenticationManager {

    private HashMap <String, User> userDatabase;
    private HashLibrary hashLibrary;

    public AuthenticationManager(HashLibrary hashLibrary) {
        this.userDatabase = new HashMap<>();
        this.hashLibrary = hashLibrary;
    }



    public boolean login(String email, String password) {
        User user = findByEmail(email);
        if (user != null) {
            String hashedPassword = hashLibrary.hashPassword(password);
            return hashedPassword.equals(user.getPassword());
        }
        return false;
    }
    public User findByEmail(String email){
        return userDatabase.get(email);
    }


}
