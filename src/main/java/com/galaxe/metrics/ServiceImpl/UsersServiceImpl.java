package com.galaxe.metrics.ServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.galaxe.metrics.Exception.ChangePasswordException;
import com.galaxe.metrics.Exception.ImageException;
import com.galaxe.metrics.Exception.UserAuthenticationException;
import com.galaxe.metrics.Exception.UserExistException;
import com.galaxe.metrics.Exception.UserNotFoundException;

import com.galaxe.metrics.Entity.Users;
import com.galaxe.metrics.Exception.UserRegistrationException;
import com.galaxe.metrics.Repository.UsersRepository;
import com.galaxe.metrics.Service.EmailService;
import com.galaxe.metrics.Service.UsersService;

@Service
public class UsersServiceImpl  {
	@Autowired
	UsersRepository userRepository;
	 @Value("${image.upload.path}")
	    private String imageUploadPath;

	 @Autowired
	 private EmailService emailService;
	 public Users register(
	            Long userId,
	            String fname,
	            String lname,
	            String email,
	            String password,
	            Long empId,
	            String designation,
	            String workLocation,
	            Long phoneNumber,
	            LocalDate dob,
	            MultipartFile image
	    ) {
	        try {
	            byte[] imageBytes = null;
	            if(userRepository.existsByEmail(email))
	            {
	            	throw new UserExistException("Your account already exists");
	            }
	            if (image != null && !image.isEmpty()) {
	                imageBytes = image.getBytes();
	            }

	            Users user = new Users(
	                    userId, fname, lname, email, password, empId, designation,
	                    workLocation, phoneNumber, imageBytes, dob, true);

	            user.setStatus(true);

	            return userRepository.save(user);
	        } catch (IOException e) {
	            // Handle the exception (log it, throw a custom exception, etc.)
	            throw new RuntimeException("Error processing image in registration", e);
	        }
	    }
	   
	
	
	public Optional<Users> loginUser(String email, String password) {
		// Authenticate a user based on email and password
		Optional<Users> authenticatedUser = userRepository.findByEmailAndPassword(email, password);
		if (authenticatedUser.isPresent()) {
			return authenticatedUser;
		} else {

			throw new UserAuthenticationException("User authentication failed");
		}

	}

	
	public void updateUser(Long userId, Users updatedUser) {

		if (userRepository.existsById(userId)) {
			updatedUser.setUserId(userId);
			userRepository.save(updatedUser);
		} else {
			throw new UserNotFoundException("User not found for id" + userId);
		}
	}

	
	public String changePassword(Long userId, String newPassword) {
		try {
			Optional<Users> optionalUser = userRepository.findById(userId);

			if (optionalUser.isPresent()) {
				Users user = optionalUser.get();
				user.setPassword(newPassword);
				userRepository.save(user);
				return "Password changed successfully";
			} else {
				throw new UserNotFoundException("No such User Exists");
			}
		} catch (ChangePasswordException e) {
			throw new ChangePasswordException("Failed to change password");
		}
	}

	
	public Optional<Users> getUserById(Long userId) {
		Optional<Users> user = userRepository.findById(userId);
		if (user.isPresent())
			return user;
		else {
			throw new UserNotFoundException("User not found exception" + userId);
		}
	}

	public void deleteUser(Long userId) {
		Optional<Users> users = userRepository.findById(userId);
		if (users.isPresent()) {
			Users user = users.get();
			user.setStatus(false);
			userRepository.save(user);
		} else {
			throw new UserNotFoundException("User not found for user id: " + userId);
		}
	}
	@Scheduled(fixedRate = 86400000) // 24 hours (24 * 60 * 60 * 1000 milliseconds)
	public void sendBirthdayEmails() {
	    LocalDate today = LocalDate.now();
	    String formattedToday = today.toString(); // Format today's date as a string

	    List<Users> usersWithBirthday = userRepository.findByDobMonthAndDobDay(formattedToday);

	    for (Users user : usersWithBirthday) {
	    	emailService.sendBirthdayMail(user.getEmail());
	    }
	}

	

}
