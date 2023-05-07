package kg.mega.RentCar.mapper;

import kg.mega.RentCar.model.Address;
import kg.mega.RentCar.model.Price;
import kg.mega.RentCar.model.dto.AddressDTO;
import kg.mega.RentCar.model.dto.PriceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE= Mappers.getMapper(PriceMapper.class);
    Price toEntity(PriceDTO priceDTO);
    PriceDTO toDto(Price price);
    List<Price> toEntityList(List<PriceDTO>priceDTOList);
    List<PriceDTO>toDTOList(List<Price>priceList);

}
