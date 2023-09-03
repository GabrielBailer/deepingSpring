package com.deepeningInSpringBoot.domain.user;

import com.deepeningInSpringBoot.dto.UserDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO data){
        this.name = data.name();
        this.lastName = data.lastName();
        this.balance = data.balance();
        this.userType = data.userType();
        this.password = data.passowrd();
        this.email = data.email();
    }
}
