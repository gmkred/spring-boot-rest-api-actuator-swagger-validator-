package net.gmkr.springboot.mapper;

import net.gmkr.springboot.dto.UserDTO;
import net.gmkr.springboot.entity.User;

public class UserMapper {

	public static UserDTO maptoUserDto(User user) {
		UserDTO userDto = new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
		return userDto;
	}

	public static User mapToUser(UserDTO userDto) {
		User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
		return user;
	}
}
