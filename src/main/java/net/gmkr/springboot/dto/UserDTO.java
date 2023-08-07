package net.gmkr.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "UserDTO model implementation")
public class UserDTO {
	private Long id;
	// user first name should not be null or empty
	@Schema(description = "User First Name")
	@NotEmpty(message = "User First Name should not be null")
	private String firstName;
	@Schema(description = "User Last Name")
	@NotEmpty(message = "User Last Name should not be null")
	private String lastName;
	@Schema(description = "User Email Address")
	@NotEmpty(message = "User Email should not be null or empty")
	@Email(message = "Email address should be valid")
	private String email;
}
