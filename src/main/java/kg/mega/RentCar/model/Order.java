package kg.mega.RentCar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@ToString
@Table(name = "tb_orderDetails")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    boolean babySeat;
    boolean withDriver;
    String clientName;
    String clientPhone;
    String clientEmail;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dateTimeFrom;
    @JsonFormat(pattern = "dd-MM-yyyy")
    LocalDate dateTimeTo;
    Double priceBeforeDiscount;
    Double priceWithDiscount;
    @ManyToOne
    Discount discount;
    @OneToOne
    Address Address;
    @OneToOne
    Address returnAddress;
    @ManyToOne
    Price price;
    @OneToOne Car car;

}
