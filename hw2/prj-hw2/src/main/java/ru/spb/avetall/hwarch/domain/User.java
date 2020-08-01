package ru.spb.avetall.hwarch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@Table(name = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDateTime birthDate;
    private String description;
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, updatable= false)
    private String password;

    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
