package net.gmkr.springboot.controller;

import java.lang.reflect.Executable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.service.annotation.PutExchange;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import net.gmkr.springboot.dto.UserDTO;
import net.gmkr.springboot.entity.User;
import net.gmkr.springboot.exception.ErroDetails;
import net.gmkr.springboot.exception.ResourceNotFoundException;
import net.gmkr.springboot.service.UserService;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@Tag(name = "CRUD REST APIs For User Resource.", description = "create user, update user, get all users, delete user")
public class UserController {

	private UserService service;

	@PostMapping("/create")
	@Operation(summary = "Create User REST API", description = "Create user REST API is used to save user to the database")
	@ApiResponse(responseCode = "201", description = "HTTP STATUS 201 CREATED.")
	public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserDTO user) {
		UserDTO savedUserDto = service.createUser(user);
		return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
	}

	@Operation(summary = "Get User by id REST API", description = "Get Single user REST API  from the database")
	@ApiResponse(responseCode = "200", description = "HTTP STATUS 200 OK.")
	@GetMapping("{id}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("id") Long id) {
		UserDTO userDto = service.getUserById(id);
		return ResponseEntity.ok(userDto);
	}

	@Operation(summary = "Get ALL Users REST API", description = "Get ALl Users from Database")
	@ApiResponse(responseCode = "200", description = "HTTP STATUS 200 OK.")
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		List<UserDTO> users = service.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@PutMapping("/{id}")
	@Operation(summary = "Update User REST API", description = "Updata user REST API is used to update user.")
	@ApiResponse(responseCode = "200", description = "HTTP STATUS 200 OK.")

	public ResponseEntity<UserDTO> updateUser(@RequestBody @Valid UserDTO userDto, @PathVariable("id") Long id) {
		userDto.setId(id);
		UserDTO updatedUserDto = service.updateUser(userDto);
		return ResponseEntity.ok(updatedUserDto);
	}

	@Operation(summary = "Delete User REST API", description = "Delete user REST API is used to delete user from the database")
	@ApiResponse(responseCode = "200", description = "HTTP STATUS 200 OK.")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
		service.deleteUser(id);
		return ResponseEntity.ok("User successfully deleted");
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErroDetails> resourceNotFoundException(ResourceNotFoundException exception,
//			WebRequest request) {
//		ErroDetails details = new ErroDetails(LocalDateTime.now(), exception.getMessage(),
//				request.getDescription(false), "USER_NOT_FOUND");
//		return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
//	}

}
