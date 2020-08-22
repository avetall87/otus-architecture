package ru.spb.avetall.hwarch.service.mapper;

import ru.spb.avetall.hwarch.domain.User;
import ru.spb.avetall.hwarch.dto.UserDto;

import static ru.spb.avetall.hwarch.util.DateUtil.localDateTimeToString;
import static ru.spb.avetall.hwarch.util.DateUtil.toLocalDateTime;

public class UserMapper {
    public static User mapToUser (UserDto userDto) {
        return User.builder()
                   .id(userDto.getId())
                   .firstName(userDto.getFirstName())
                   .lastName(userDto.getLastName())
                   .patronymic(userDto.getPatronymic())
                   .birthDate(toLocalDateTime(userDto.getBirthDate()))
                   .description(userDto.getDescription())
                   .phone(userDto.getPhone())
                   .email(userDto.getEmail())
                   .creationDate(toLocalDateTime(userDto.getCreationDate()))
                   .modificationDate(toLocalDateTime(userDto.getModificationDate()))
                   .build();
    }

    public static UserDto mapToUserDto(User user) {
        return UserDto.builder()
                      .id(user.getId())
                      .firstName(user.getFirstName())
                      .lastName(user.getLastName())
                      .patronymic(user.getPatronymic())
                      .birthDate(localDateTimeToString(user.getBirthDate()))
                      .description(user.getDescription())
                      .phone(user.getPhone())
                      .email(user.getEmail())
                      .creationDate(localDateTimeToString(user.getCreationDate()))
                      .modificationDate(localDateTimeToString(user.getModificationDate()))
                      .build();
    }
}
