package ru.spb.avetall.hwarch.service;

import ru.spb.avetall.hwarch.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    List<UserDto> findByFirstName(String firstName);
    UserDto findByEmail(String email);
    
    void deleteById(Long id);
    void deleteByEmail(String email);
    void update(UserDto userDto);
    void updateByEmail(UserDto userDto);
    void create(UserDto userDto);
}
