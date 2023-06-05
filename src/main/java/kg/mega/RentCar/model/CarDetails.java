package kg.mega.RentCar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@Table(name = "tb_carDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class CarDetails {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate reservedDates ;
    @OneToOne
   private Car car;
    @OneToOne
    private  Order order;

}
