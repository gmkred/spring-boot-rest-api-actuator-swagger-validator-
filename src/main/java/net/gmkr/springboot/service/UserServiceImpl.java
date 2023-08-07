package net.gmkr.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.gmkr.springboot.dto.UserDTO;
import net.gmkr.springboot.entity.User;
import net.gmkr.springboot.exception.EmailAlreadyExistsException;
import net.gmkr.springboot.exception.ResourceNotFoundException;
import net.gmkr.springboot.mapper.AutoUserMapper;
import net.gmkr.springboot.mapper.UserMapper;
import net.gmkr.springboot.repository.UserRepository;

@Service // component for auto scanning
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDto) {
		// convert userdto to user jpa entity
//		User user = UserMapper.mapToUser(userDto);
//		User user = modelMapper.map(userDto, User.class);
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());
		if (optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exists for User");
		}
		User user = AutoUserMapper.INSTANCE.mapToUser(userDto);

		User savedUser = userRepository.save(user);
//		UserDTO savedUserDto = UserMapper.maptoUserDto(savedUser);
//		UserDTO savedUserDto = modelMapper.map(savedUser, UserDTO.class);
		UserDTO savedUserDto = AutoUserMapper.INSTANCE.mapToUserDto(savedUser);

		return savedUserDto;
	}

	@Override
	public UserDTO getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
//		User retrievedUser = optionalUser.get();
//		UserDTO userDto = UserMapper.maptoUserDto(retrievedUser);
//		UserDTO userDto = modelMapper.map(retrievedUser, UserDTO.class);
		UserDTO userDto = AutoUserMapper.INSTANCE.mapToUserDto(user);

		return userDto;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepository.findAll();
//		return users.stream().map(UserMapper::maptoUserDto).collect(Collectors.toList());
//		return users.stream().map((user) -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
		return users.stream().map((user) -> AutoUserMapper.INSTANCE.mapToUserDto(user)).collect(Collectors.toList());

	}

	@Override
	public UserDTO updateUser(UserDTO userDto) {
		User existingUser = userRepository.findById(userDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userDto.getId()));
		existingUser.setFirstName(userDto.getFirstName());
		existingUser.setLastName(userDto.getLastName());
		existingUser.setEmail(userDto.getEmail());
		User updatedUser = userRepository.save(existingUser);
//		return UserMapper.maptoUserDto(updatedUser);
//		return modelMapper.map(updatedUser, UserDTO.class);
		return AutoUserMapper.INSTANCE.mapToUserDto(updatedUser);

	}

	@Override
	public void deleteUser(Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.deleteById(id);
	}

}
