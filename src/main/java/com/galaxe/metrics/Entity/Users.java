package com.galaxe.metrics.Entity;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long userId;

	@NotBlank(message = "First Name is required")
	@Size(max = 50, message = "Name must be less than 50 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
	@Column(updatable = false)
	String fname;

	@NotBlank(message = "Last Name is required")
	@Size(max = 50, message = "Name must be less than 50 characters")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
	@Column(updatable = false)
	String lname;

	@NotBlank(message = "Email is required")
	@Size(max = 255, message = "Email must be less than 255 characters")
	String email;

	@NotBlank(message = "Password is required")
	@Size(min = 8, message = "Password must be at least 8 characters")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace")
	String password;

	@NotNull(message = "EmpId id required")
	@Column(unique = true, nullable = false)
	Long empId;

	String designation;
	String workLocation;

	Long phoneNumber;

	@Lob
	@Column(name = "image",columnDefinition = "VARBINARY(MAX)")
	private byte[] image;
	
	LocalDate dob;

	public Users(
			@NotBlank(message = "First Name is required") @Size(max = 50, message = "Name must be less than 50 characters") @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters") String fname,
			@NotBlank(message = "Last Name is required") @Size(max = 50, message = "Name must be less than 50 characters") @Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters") String lname,
			@NotBlank(message = "Email is required") @Size(max = 255, message = "Email must be less than 255 characters") String email,
			@NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters") @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, one special character, and no whitespace") String password,
			@NotNull(message = "EmpId id required") Long empId, String designation, String workLocation,
			Long phoneNumber,LocalDate dob, byte[] image, Boolean status) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.empId = empId;
		this.designation = designation;
		this.workLocation = workLocation;
		this.phoneNumber = phoneNumber;
		this.image = image;
		this.dob=dob;
		this.status = status;
	}

	Boolean status;
}
