package kg.mega.RentCar.model.dto;
import kg.mega.RentCar.model.enums.Category;
import kg.mega.RentCar.model.enums.Color;
import kg.mega.RentCar.model.enums.EngineType;
import kg.mega.RentCar.model.enums.Transmission;
import lombok.Data;

@Data
public class CarDTO {
    Long id;
    String model;
    String photo;
    String mnfYear;
    String description;
    //enum
    // @Enumerated(value = EnumType.STRING)
    Color color;
    //  @Enumerated(value = EnumType.STRING)
    EngineType engineType;
    String engineVolume;
    Integer gasPer100;
    // @Enumerated(value = EnumType.STRING)
    Category category;
    // @Enumerated(value = EnumType.STRING)
    Transmission transmission;
    boolean isAvailable;


}
