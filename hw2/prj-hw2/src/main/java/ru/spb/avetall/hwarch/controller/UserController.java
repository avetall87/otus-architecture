package ru.spb.avetall.hwarch.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.spb.avetall.hwarch.dto.UserDto;
import ru.spb.avetall.hwarch.service.UserService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
@Api(value="Контроллер для работы с пользователями", description="CRUD операции для работы с пользователями")
public class UserController {
    
    private final UserService userService;

    @GetMapping("/find/all")
    @ApiOperation(value = "Возвращает всех пользователей", response = List.class)
    public List<UserDto> getAll() {
        return userService.findAll();        
    }
    
    @GetMapping("/find/firstName/{firstName}")
    @ApiOperation(value = "Возвращает всех пользователей по имени пользователя", response = List.class)
    public List<UserDto> getByFirstName(@PathVariable String firstName) {
        return userService.findByFirstName(firstName);
    }

    @GetMapping("/find/email/{email}")
    @ApiOperation(value = "Возвращает пользователя по алресу электронной почты", response = UserDto.class)
    public UserDto getByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @PostMapping(value = "/create")
    @ApiOperation(value = "Созадет нового пользователя", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь успешно создан"),
            @ApiResponse(code = 500, message = "При создании пользователя произошла ошибка")
    }
    )
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        try {
            userService.create(userDto);
            return ResponseEntity.ok().build();
        } catch (Throwable th) {
            log.error("User controller. Create user exception", th);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(th.getMessage());
        }
    }
    
    @PutMapping(value = "/update")
    @ApiOperation(value = "Обновляет данные пользователя", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные пользователь успешно обновлены"),
            @ApiResponse(code = 500, message = "При обновлении данных пользователя произошла ошибка")
    }
    )
    public ResponseEntity<String> update(@RequestBody UserDto userDto) {
        try {
            userService.update(userDto);
            return ResponseEntity.ok().build();
        } catch (Throwable th) {
            log.error("User controller. Update user exception", th);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(th.getMessage());            
        }
    }

    @PutMapping(value = "/update/email")
    @ApiOperation(value = "Обновляет данные пользователя по электронной почте", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Данные пользователь успешно обновлены"),
            @ApiResponse(code = 500, message = "При обновлении данных пользователя произошла ошибка")
    }
    )
    public ResponseEntity<String> updateByEmail(@RequestBody UserDto userDto) {
        try {
            userService.updateByEmail(userDto);
            return ResponseEntity.ok().build();
        } catch (Throwable th) {
            log.error("User controller. Update user exception", th);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(th.getMessage());
        }
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "Удаляет пользователя по идентификатору пользователя", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь успешно удален"),
            @ApiResponse(code = 500, message = "При удалении пользователя произошла ошибка")
    }
    )
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Throwable th) {
            log.error("User controller. Delete user by id exception", th);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(th.getMessage());
        }
    }

    @DeleteMapping("/delete/email/{email}")
    @ApiOperation(value = "Удаляет пользователя по электронной почте пользователя", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Пользователь успешно удален"),
            @ApiResponse(code = 500, message = "При удалении пользователя произошла ошибка")
    }
    )
    public ResponseEntity<String> deleteByEmail(@PathVariable String email) {
        try {
            userService.deleteByEmail(email);
            return ResponseEntity.ok().build();
        } catch (Throwable th) {
            log.error("User controller. Delete user by email exception", th);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(th.getMessage());
        }
    }
    
}
