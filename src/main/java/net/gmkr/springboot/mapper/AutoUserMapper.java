package net.gmkr.springboot.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.gmkr.springboot.dto.UserDTO;
import net.gmkr.springboot.entity.User;

@Mapper
public interface AutoUserMapper {

	AutoUserMapper INSTANCE = Mappers.getMapper(AutoUserMapper.class);

	// @Mapping(source = "email", target = "email")
	UserDTO mapToUserDto(User user);

	User mapToUser(UserDTO userDto);
}
