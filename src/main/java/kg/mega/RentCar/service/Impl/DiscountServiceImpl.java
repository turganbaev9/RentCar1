package kg.mega.RentCar.service.Impl;

import kg.mega.RentCar.mapper.DiscountMapper;
import kg.mega.RentCar.model.Discount;
import kg.mega.RentCar.model.dto.DiscountDTO;
import kg.mega.RentCar.repository.CarRepo;
import kg.mega.RentCar.repository.DiscountRepo;
import kg.mega.RentCar.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepo discountRep;
    private final CarRepo carRep;
    @Override
    public DiscountDTO save(DiscountDTO discountDTO) {
        Discount discount = DiscountMapper.INSTANCE.toEntity(discountDTO);
        discount.setCar(carRep.findById(discount.getCar().getId()).get());
        calculateDiscount(Collections.singletonList(discountDTO));
        Discount saveDiscount = discountRep.save(discount);
        System.err.println(saveDiscount);
        return DiscountMapper.INSTANCE.toDto(saveDiscount);
    }

    @Override
    public DiscountDTO update(DiscountDTO discountDTO) {
        Discount discount = DiscountMapper.INSTANCE.toEntity(discountDTO);
        Discount updateDiscount = discountRep.findById(discount.getId()).get();
        updateDiscount.setCar(discount.getCar());
        updateDiscount.setStartDate(discount.getStartDate());
        updateDiscount.setEndDate(discount.getEndDate());
        return DiscountMapper.INSTANCE.toDto(updateDiscount);
    }

    @Override
    public DiscountDTO findById(Long id) {
        return DiscountMapper.INSTANCE.toDto(discountRep.findById(id).get());
    }

    @Override
    public List<DiscountDTO> findAllDiscount() {
        List<Discount> discounts = discountRep.findAll();
        if (discounts == null) {
            return null;
        }
        return DiscountMapper.INSTANCE.toDTOList(discounts);
    }

    @Override
    public void deleteDiscount(Long id) {
        discountRep.deleteById(id);
    }

    @Override
    public List<DiscountDTO> calculateDiscount(List<DiscountDTO> discountDTOList) {
        if (discountDTOList == null) {
            return Collections.emptyList();
        }
        for (DiscountDTO discountDTo : discountDTOList) {
            LocalDate startDate = discountDTo.getStartDate();
            LocalDate endDate = discountDTo.getEndDate();
            Period period = Period.between(startDate, endDate);
            int daysbetween = period.getDays();
            discountDTo.setCountDay(daysbetween);
            if (daysbetween > 10) {
                double discount = 0.4;//40%
                discountDTo.setDiscount(discount);
            } else if (daysbetween >= 5) {
                double discount = 0.2;//20%
                discountDTo.setDiscount(discount);
            } else {
                double discount = 0;//without discount
                discountDTo.setDiscount(discount);
            }
        }
        return discountDTOList;
    }
}
