package ru.spb.avetall.hwarch.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.spb.avetall.hwarch.domain.User;
import ru.spb.avetall.hwarch.dto.UserDto;
import ru.spb.avetall.hwarch.respository.UserRepository;
import ru.spb.avetall.hwarch.service.HashingService;
import ru.spb.avetall.hwarch.service.UserService;
import ru.spb.avetall.hwarch.service.exception.UserOrIdIsNullException;
import ru.spb.avetall.hwarch.service.exception.UserOrPasswordIsNullException;
import ru.spb.avetall.hwarch.service.mapper.UserMapper;

import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.lang.String.*;
import static java.util.Objects.*;
import static org.thymeleaf.util.StringUtils.*;
import static ru.spb.avetall.hwarch.service.mapper.UserMapper.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final HashingService hashingService;
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .filter(Objects::nonNull)
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> findByFirstName(String firstName) {
        if(isEmpty((firstName))) {
            throw new IllegalArgumentException("Cant't find users, incoming firstName is null !");
        }

        return userRepository.findByFirstName(firstName).stream()
                .filter(Objects::nonNull)
                .map(UserMapper::mapToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findByEmail(String email) {
        if(isEmpty((email))) {
            throw new IllegalArgumentException("Cant't find user, incoming email is null !");
        }
        
        return mapToUserDto(userRepository.findByEmail(email));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (isNull(id)) {
            throw new IllegalArgumentException("User id is null, can't delete current user !");
        }

        if (userRepository.existsUserById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException(format("User with id %s not found !", id));
        }

    }

    @Override
    @Transactional
    public void update(UserDto userDto) {
        if (isNull(userDto) || isNull(userDto.getId())) {
            throw new UserOrIdIsNullException("User or id is null, can't update current user !");
        }

        userRepository.save(mapToUser(userDto));
    }

    @Override
    @Transactional
    public void create(UserDto userDto) {
        if (isNull(userDto) || isNull(userDto.getPassword())) {
            throw new UserOrPasswordIsNullException("User or password is null, can't create new user !");
        }

        byte[] password = hashingService.generateHash(userDto.getPassword());

        User user = mapToUser(userDto);
        user.setPassword(Base64.getEncoder().encodeToString(password));

        userRepository.save(user);

    }
}
