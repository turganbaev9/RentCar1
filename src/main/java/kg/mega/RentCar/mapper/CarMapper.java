package kg.mega.RentCar.mapper;
import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.dto.CarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CarMapper {


    CarMapper INSTANCE= Mappers.getMapper(CarMapper.class);
    Car toEntity(CarDTO car);
    CarDTO toDto(Car car);
    List<Car> toEntityList(List<CarDTO>carDTOList);
    List<CarDTO>toDTOList(List<Car >carList);
}
