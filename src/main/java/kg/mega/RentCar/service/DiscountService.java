package kg.mega.RentCar.service;

import kg.mega.RentCar.model.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {
    DiscountDTO save(DiscountDTO discount);
    DiscountDTO update(DiscountDTO discountDTO);
    DiscountDTO findById(Long id);
    List<DiscountDTO> findAllDiscount();
    void deleteDiscount(Long id);
    List<DiscountDTO> calculateDiscount(List<DiscountDTO> discountDTOList);
}
