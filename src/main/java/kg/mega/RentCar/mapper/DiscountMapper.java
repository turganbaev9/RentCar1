package kg.mega.RentCar.mapper;


import kg.mega.RentCar.model.Discount;
import kg.mega.RentCar.model.dto.DiscountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiscountMapper {

    AddressMapper INSTANCE= Mappers.getMapper(AddressMapper.class);
    Discount toEntity(DiscountDTO discountDTO);
    DiscountDTO toDto(Discount discount);
    List<Discount> toEntityList(List<DiscountDTO>discountDTOList);
    List<DiscountDTO>toDTOList(List<Discount >discounts);


}
