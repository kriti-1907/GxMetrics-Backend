package com.galaxe.metrics.Service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.galaxe.metrics.Entity.Users;

public interface UsersService {

	Optional<Users> loginUser(String email, String password);
	void updateUser(Long productId, Users user);
	String changePassword(Long userId, String newPassword);
	Optional<Users> getUserById(Long userId);
	void deleteUser(Long userId);
	  //Users saveUserWithImage(Users user, MultipartFile imageFile) throws IOException ;
	 void sendBirthdayEmails();
}
