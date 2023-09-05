package kr.pe.speech.webbiz.domain;


import kr.pe.speech.webbiz.entity.BaseEntity;
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
public class Project extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column( nullable = false, unique = true )
    private String projectName;

    @Column(nullable = true)
    private String projectType;

    @Column( nullable = true )
    private String urlName;

    @Column( nullable = true )
    private String sourceLang;

    @Column( nullable = true )
    private String targetLang;

    @Column( nullable = true )
    private String subType;

    @ManyToOne
    @JoinColumn( name="user_id")
    @ToString.Exclude
    private User user;

    /*
    // persistence context
    public void setUser(User user){
        if(this.user != null ){
            this.user.getProjectList().remove(this);
        }
        this.user = user;
        if(this.user != null){
            user.getProjectList().add(this);
        }
    }

     */

/*
    public Project(){}

    public Project(String name, String type){
        super();
        this.projectname = name;
        this.projecttype = type;
    }
*/

}
