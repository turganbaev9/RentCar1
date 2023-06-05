package kg.mega.RentCar.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;

@Data
public class CarDetailsDTO {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private  LocalDate reservedDates ;
    private CarDTO car;
    private  OrderDTO order;
}
