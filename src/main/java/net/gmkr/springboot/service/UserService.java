package net.gmkr.springboot.service;

import java.util.List;

import net.gmkr.springboot.dto.UserDTO;
import net.gmkr.springboot.entity.User;

public interface UserService {
	UserDTO createUser(UserDTO user);

	UserDTO getUserById(Long id);

	List<UserDTO> getAllUsers();

	UserDTO updateUser(UserDTO user);

	void deleteUser(Long id);
}
