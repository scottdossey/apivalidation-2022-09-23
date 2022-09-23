package com.tts.validation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	@NotEmpty
	@NotNull
	@Size(min=3, max=30)
	private String userName;
	
	@NotEmpty
	@NotNull
	@Size(max=128)
	private String firstName;
	
	@NotEmpty
	@NotNull
	@Size(max=128)
	private String lastName;
	
	@Email
	@Size(max=128)
	private String email;
	
	@Size(min=8, max=30)
	private String password;
	
	
	@PastOrPresent
	private Date createdAt;
}
