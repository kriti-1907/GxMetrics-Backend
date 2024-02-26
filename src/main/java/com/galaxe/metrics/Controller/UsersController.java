package com.galaxe.metrics.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.galaxe.metrics.Entity.Users;
import com.galaxe.metrics.Service.UsersService;
import com.galaxe.metrics.ServiceImpl.ConsumeExternalApiService;
import com.galaxe.metrics.ServiceImpl.UsersServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Users")
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/Users")
@RestController

public class UsersController {

	@Autowired
	private UsersServiceImpl usersService;

	@Autowired
	private ConsumeExternalApiService consumeExternalApiService;
	@Value("${file.upload.directory}") // Add this annotation for property injection
	private String uploadDirectory;

	@ApiOperation("user register")
	@PostMapping("/register")
	public ResponseEntity<Users> addUser(
		    @RequestParam("fname") String fname,
		    @RequestParam("lname") String lname,
		    @RequestParam("email")  String email,
		    @RequestParam("password") String password,
		    @RequestParam("empId") Long empId,
		    @RequestParam String designation,
		    @RequestParam String workLocation,
		    @RequestParam Long phoneNumber,
		    @RequestParam LocalDate dob,
		    @RequestParam(value = "image", required = false) MultipartFile image,Model model) {
		
		
			Users savedUser = usersService.register(phoneNumber, fname, lname, email, password, empId, designation, workLocation, phoneNumber,dob, image);
					
			return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
		
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Users user) {
		Optional<Users> authenticatedUser = usersService.loginUser(user.getEmail(), user.getPassword());
		return ResponseEntity.ok(authenticatedUser);

	}

	@PutMapping("/update-user/{userId}")
	public ResponseEntity<?> updateUser(@PathVariable("userId") Long userId, @RequestBody Users updatedUser) {
		usersService.updateUser(userId, updatedUser);
		return ResponseEntity.ok("User updated successfully");
	}

	@DeleteMapping("delete-user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Long userId) {
		usersService.deleteUser(userId);
		return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
	}

	@PatchMapping("/change-password/{userId}")
	public String changePassword(@PathVariable("userId") Long userId, @RequestBody Map<String, String> patchRequest) {
		String newPassword = patchRequest.get("newPassword");
		return usersService.changePassword(userId, newPassword);
	}

	@GetMapping("/getuser/{userId}")
	public ResponseEntity<Optional<Users>> getUserById(@PathVariable("userId") Long userId) {
		Optional<Users> users = usersService.getUserById(userId);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping(value = "/fetchdata")
	public List<Object> fetchData() {
		String url = "https://jsonplaceholder.typicode.com/posts";
		RestTemplate restTemplate = new RestTemplate();
		Object[] resultObjects = restTemplate.getForObject(url, Object[].class);
		return Arrays.asList(resultObjects);
	}

	@GetMapping(value = "/fetch")
	public List<Object> fetch() {
		return consumeExternalApiService.fetchUserData();
	}
}