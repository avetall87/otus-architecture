package ru.spb.avetall.hwarch.controller;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import ru.spb.avetall.hwarch.domain.User;
import ru.spb.avetall.hwarch.dto.UserDto;
import ru.spb.avetall.hwarch.respository.UserRepository;
import ru.spb.avetall.hwarch.service.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "application.yml")
class UserControllerTest {

    private static final String ALL_USERS_ETALON = "\"id\":1,\"firstName\":\"ADMIN\",\"lastName\":\"-\",\"patronymic\":\"-\",\"birthDate\":\"26.07.2020 00:00:00\",\"description\":\"Super admin !\",\"phone\":\"+7-911-123-12-12\",\"email\":\"test@mail.ru\"";

    private String baseUrl = "";

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        baseUrl = "http://localhost:" + port + "/api/v1/user";
    }

    @Test
    void getAll() {
        assertThat(this.restTemplate.getForObject(baseUrl + "/find/all", String.class)).contains(ALL_USERS_ETALON);
    }

    @Test
    void getByFirstName() {
        assertThat(this.restTemplate.getForObject(baseUrl + "/find/firstName/ADMIN", String.class)).contains(ALL_USERS_ETALON);
    }

    @Test
    void getByEmail() {
        assertThat(this.restTemplate.getForObject(baseUrl + "/find/email/test@mail.ru", String.class)).contains(ALL_USERS_ETALON);
    }

    @Test
    void create() throws JsonProcessingException {
        UserDto dto = UserDto.builder()
                .firstName("Иванов")
                .lastName("Иван")
                .patronymic("Петрович")
                .birthDate("20.03.2003")
                .description("Тестовый пользователь")
                .email("test_user@mail.ru")
                .phone("+7-981-534-51-85")
                .password("supper-strong-password")
                .build();

        String userDto = new ObjectMapper().writeValueAsString(dto);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = this.restTemplate.postForEntity(baseUrl + "/create",
                new HttpEntity<>(userDto, headers), String.class);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(responseEntity).isNotNull();
            softAssertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        });
    }

    @Test
    void update() {
        Optional<User> optionalUser = userRepository.findById(2L);
        User user = optionalUser.orElseThrow(()-> new RuntimeException("User is null !"));

        UserDto userDto = UserMapper.mapToUserDto(user);
        userDto.setLastName("Петрович");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> responseEntity = this.restTemplate.exchange(baseUrl + "/update", HttpMethod.PUT,
                new HttpEntity<>(userDto, headers), String.class);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(responseEntity).isNotNull();
            softAssertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        });
    }

    @Test
    void delete() {
        UserDto dto = UserDto.builder()
                .firstName("Иванов1")
                .lastName("Иван")
                .patronymic("Петрович")
                .birthDate("20.03.2003")
                .description("Тестовый пользователь")
                .email("test1_user@mail.ru")
                .phone("+7-981-534-51-87")
                .password("supper-strong-password")
                .build();

        User mapToUser = UserMapper.mapToUser(dto);
        mapToUser.setPassword("pass");

        userRepository.save(mapToUser);

        User user = userRepository.findByFirstName("Иванов1").get(0);

        Map<String, Long> params = new HashMap<>();
        params.put("id", user.getId());
        restTemplate.delete(baseUrl + "/delete/{id}", params);

    }

}