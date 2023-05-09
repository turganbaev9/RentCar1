package kg.mega.RentCar.mapper;
import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.dto.CarDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Mapper
@Configuration
public interface CarDetailsMapper {
    CarDetailsMapper INSTANCE = Mappers.getMapper(CarDetailsMapper.class);

    CarDetails toEntity(CarDetailsDTO carDetailsDTO);

    CarDetailsDTO toDto(CarDetails carDetails);

    List<CarDetails> toEntityList(List<CarDetailsDTO> carDetailsDTOS);

    List<CarDetailsDTO> toDTOList(List<CarDetails> carDetails);
}
