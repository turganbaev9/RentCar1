package kg.mega.RentCar.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;


import java.time.LocalDate;


public class CarDetailsDTO {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate reservedDates ;
    private CarDTO carDTO;

}
