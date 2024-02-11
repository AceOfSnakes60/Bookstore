package org.example;

import java.util.HashMap;
import java.util.Map;

public class User {
    private Long userID;
    private String username;
    private String email;
    private String password;

    private Map<String, Integer> reserved;

    public User(String username, String email, String password){;
        this.username = username;
        this.email = email;
        this.password = password;
        reserved = new HashMap<>();
    }

    public String getUsername(){return username;}
    public String getEmail(){return email;}

    @Override
    public String toString(){
        return "username: " + username + "\n" + "email: " + email + "\n" + "password: " + password + "\n";
    }

    public void setUserID(Long userID){ this.userID = userID;}


    public Long getUserId() { return userID; }

    public boolean checkPassword(String input2) {
        return password.equals(input2);
    }

    public void reserveBook(String title, int count){

        if(reserved.containsKey(title)){
            count += reserved.get(title);
        }
        reserved.put(title, count);
    }
}
