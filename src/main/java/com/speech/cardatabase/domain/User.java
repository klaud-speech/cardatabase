package com.speech.cardatabase.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Table(name="user")
@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    public User(){}

    public User(String name, String password, String role){
        super();
        this.username = name;
        this.password = password;
        this.role = role;
    }
}
