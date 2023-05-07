package kg.mega.RentCar.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class OrderDTO {
    private Long id;
    private boolean babySeat;
    private boolean withDriver;
    private String clientName;
    private String clientPhone;
    private String clientEmail;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTimeFrom;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateTimeTo;
    private Double priceBeforeDiscount;
    private Double priceWithDiscount;
    private CarDTO car;
    AddressDTO address;
    AddressDTO returnAddress;
    private DiscountDTO discount;
    private PriceDTO price;

}

