package com.speech.cardatabase.entity;

import lombok.*;

import javax.persistence.*;
import java.lang.invoke.CallSite;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name="product")

public class Product extends BaseEntity{  //제품 ( <- 공급자)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable=false)
    private String name;

    @Column(nullable=false)
    private Integer price;

    @Column(nullable=false)
    private Integer stock;


    @ManyToOne
    @JoinColumn(name="provider_id")
    @ToString.Exclude
    private Provider provider;



}
