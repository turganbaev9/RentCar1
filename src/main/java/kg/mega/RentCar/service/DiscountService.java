package kg.mega.RentCar.service;

import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.Discount;
import kg.mega.RentCar.model.Order;
import kg.mega.RentCar.model.Price;
import kg.mega.RentCar.model.dto.DiscountDTO;

import java.time.LocalDate;
import java.util.List;

public interface DiscountService {
    DiscountDTO save(DiscountDTO discount);
    DiscountDTO update(DiscountDTO discountDTO);
    DiscountDTO findById(Long id);
    List<DiscountDTO> findAllDiscount();
    void deleteDiscount(Long id);
    Discount findActive(Car car, Integer daysCount);

}
