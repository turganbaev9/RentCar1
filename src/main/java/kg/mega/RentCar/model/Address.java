package kg.mega.RentCar.model;

import lombok.*;

import javax.persistence.*;
@Entity
@Table(name = "tb_address")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String city;
        String street;
        Integer buildingNum;

    }

