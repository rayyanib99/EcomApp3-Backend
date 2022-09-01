package com.rayyan.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.rayyan.Model.User;
import com.rayyan.Repository.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController 
{
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/")
    public List<User> listAllUsers()
    {
        return userRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id)
    {
    	return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public void createUser(@RequestBody User user) throws NoSuchAlgorithmException 
    {
    	// Create MessageDigest instance for MD5
    	MessageDigest md = MessageDigest.getInstance("MD5");

        // Add password bytes to digest
        md.update(user.getPassword().getBytes());

        // Get the hash's bytes
        byte[] bytes = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) 
        {
        	sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        user.setPassword(sb.toString());
        userRepository.save(user);
    }
    
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id)
    {
        userRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id) 
    {
        User user1 = userRepository.findById(user.getUserId()).get();
        user1.setEmail(user.getEmail());
        user1.setCountry(user.getCountry());
        user1.setZipCode(user.getZipCode());
        user1.setCity(user.getCity());
        user1.setState(user.getState());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setAddress(user.getAddress());
        user1.setIsAdmin(user.getIsAdmin());
        
        userRepository.save(user1);
        return user;
    }
}