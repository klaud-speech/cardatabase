package kr.pe.speech.webbiz.domain;


import lombok.*;

import javax.persistence.*;

@Data
@Table(name="project")
@Entity
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column( nullable = false, unique = true )
    private String projectname;

    @Column(nullable = true)
    private String projecttype;


    public Project(){}

    public Project(String name, String type){
        super();
        this.projectname = name;
        this.projecttype = type;
    }

}
