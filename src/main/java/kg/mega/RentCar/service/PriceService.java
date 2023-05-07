package kg.mega.RentCar.service;

import kg.mega.RentCar.model.dto.PriceDTO;

import java.util.List;

public interface PriceService {
    PriceDTO save(PriceDTO priceDTO);
    PriceDTO update(PriceDTO priceDTO);
    PriceDTO finById(Long id);
    List<PriceDTO> findAll();
    void deletePrice(Long id);
}
