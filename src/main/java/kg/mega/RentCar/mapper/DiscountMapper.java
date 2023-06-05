package kg.mega.RentCar.mapper;


import kg.mega.RentCar.model.Discount;
import kg.mega.RentCar.model.dto.DiscountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE= Mappers.getMapper(DiscountMapper.class);
    Discount toEntity(DiscountDTO discountDTO);
    DiscountDTO toDto(Discount discount);
    List<Discount> toEntityList(List<DiscountDTO>discountDTOList);

    List<DiscountDTO> toDtoList(List<Discount>discountList);

}
