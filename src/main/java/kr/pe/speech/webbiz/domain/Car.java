package kr.pe.speech.webbiz.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class Car {
    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    private String brand;
    private String model;
    private String color;
    private String registerNumber;
    private int _year;
    private int price;

    @ManyToOne( fetch=FetchType.LAZY )
    @JoinColumn(name="owner")
    private Owner owner;

    public Car(String brand, String model, String color, String registerNumber, int year, int price, Owner owner ){
        super();
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.registerNumber = registerNumber;
        this._year = year;
        this.price = price;
        this.owner = owner;
    }



}
