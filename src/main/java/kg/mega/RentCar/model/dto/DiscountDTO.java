package kg.mega.RentCar.model.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
@Data
public class DiscountDTO {

    private Long id;
    private CarDTO car;
    @JsonFormat(pattern= "dd-MM-yyyy")
    private LocalDate startDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private  LocalDate endDate;
    private  Integer CountDay;
    private  Double discount;
}
