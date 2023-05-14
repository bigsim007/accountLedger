package za.co.bigsim.bankingApi.service;

import za.co.bigsim.bankingApi.entity.dto.UserDto;
import za.co.bigsim.bankingApi.entity.User;

import java.util.List;

public interface UserService {

    User saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();

    public UserDto mapToUserDto(User user);

    public User getUserfromDto(UserDto userDto);

}
