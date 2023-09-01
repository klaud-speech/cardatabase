package kr.pe.speech.webbiz.domain;


import lombok.*;

import javax.persistence.*;

//@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Table(name="project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column( nullable = false, unique = true )
    private String projectname;

    @Column(nullable = true)
    private String projecttype;

    @ManyToOne
    @JoinColumn( name="user_id")
    @ToString.Exclude
    private User user;

/*
    public Project(){}

    public Project(String name, String type){
        super();
        this.projectname = name;
        this.projecttype = type;
    }
*/
}
