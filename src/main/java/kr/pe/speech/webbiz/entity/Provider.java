package kr.pe.speech.webbiz.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name="provider")  // 공급업체
public class Provider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private  String name;

    @OneToMany(mappedBy="provider", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();

}
