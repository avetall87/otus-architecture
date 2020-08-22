package ru.spb.avetall.hwarch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private String birthDate;
    private String description;
    private String phone;
    private String email;
    private String password;
    private String creationDate;
    private String modificationDate;
}
