package kr.pe.speech.webbiz.domain;

import kr.pe.speech.webbiz.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Data

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@AllArgsConstructor
@Table(name="user")  // login-account
public class User extends BaseEntity {

    // Auto Incremental Key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    /*
    // UUID
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name="uuid_user_id", columnDefinition = "BINARY(16)")
    private UUID id;
    */


    //@Column(nullable = false, unique = true)
    private String username;

    //@Column(nullable = false)
    private String password;

    //@Column(nullable = false)
    private String role;

    @OneToMany( mappedBy = "user", fetch=FetchType.EAGER )
    @ToString.Exclude
    private List<Project> projectList = new ArrayList<>();

    /*
    @OneToMany(fetch = FetchType.EAGER )
    @JoinColumn(name="user_id")
    private List<Project> projects = new ArrayList<>();
    */
/*
    public User(){}

    public User(String name, String password, String role){
        super();
        this.username = name;
        this.password = password;
        this.role = role;
    }

 */
}
