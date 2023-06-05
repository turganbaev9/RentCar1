package kg.mega.RentCar.service.Impl;

import kg.mega.RentCar.mapper.PriceMapper;
import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.Price;
import kg.mega.RentCar.model.dto.PriceDTO;
import kg.mega.RentCar.repository.CarRepo;
import kg.mega.RentCar.repository.PriceRepo;
import kg.mega.RentCar.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class PriceServiceImpl implements PriceService {
    private final CarRepo carRepo;
    private final PriceRepo priceRepo;
    @Override
    public PriceDTO save(PriceDTO priceDTO) {
        Price price= PriceMapper.INSTANCE.toEntity(priceDTO);
      Car car=carRepo.findById(price.getCar().getId()).orElse(null);
      price.setCar(car);
      price=priceRepo.save(price);
      return PriceMapper.INSTANCE.toDto(price);
    }
    @Override
    public PriceDTO update(PriceDTO priceDTO) {
        Price price = PriceMapper.INSTANCE.toEntity(priceDTO);
        Price priceUpdate = priceRepo.findById(price.getId()).get();
          priceUpdate.setPrice(price.getPrice());
/*          priceUpdate.setStartDate(priceUpdate.getStartDate());
          priceUpdate.setEndDate(priceUpdate.getEndDate());*/



        return PriceMapper.INSTANCE.toDto(priceUpdate);
    }

    @Override
    public PriceDTO finById(Long id) {
        return PriceMapper.INSTANCE.toDto(priceRepo.findById(id).get());
    }

    @Override
    public List<PriceDTO> findAll() {
        List<Price>prices=priceRepo.findAll();

        return PriceMapper.INSTANCE.toDTOList(prices);

    }

    @Override
    public void deletePrice(Long id) {
        priceRepo.deleteById(id);
    }
}
