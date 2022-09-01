package com.rayyan.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String email;
    private String password;
    private String isAdmin;
    
    public User(String firstName, String lastName, String phoneNumber, String address, String city, String state, String zipCode, String country, String email, String password, String isAdmin) throws NoSuchAlgorithmException 
    {
    	// Create MessageDigest instance for MD5
    	MessageDigest md = MessageDigest.getInstance("MD5");

        // Add password bytes to digest
        md.update(password.getBytes());

        // Get the hash's bytes
        byte[] bytes = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) 
        {
        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.email = email;
        this.password = sb.toString();
        this.isAdmin = isAdmin;
    }
}
