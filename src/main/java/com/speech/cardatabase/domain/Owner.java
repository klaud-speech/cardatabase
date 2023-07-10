package com.speech.cardatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )

    private long ownerid;
    private String firstName;
    private String lastName;

    public Owner(String firstName, String lastName){
        super();
        this.firstName = firstName;
        this.lastName  = lastName;
    }

    @JsonIgnore
    @OneToMany( cascade=CascadeType.ALL, mappedBy = "owner")
    private List<Car> cars;
}
