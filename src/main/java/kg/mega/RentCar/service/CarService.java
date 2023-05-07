package kg.mega.RentCar.service;

import kg.mega.RentCar.model.dto.CarDTO;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    CarDTO save(CarDTO car);
    CarDTO update(CarDTO carDTO);
    CarDTO findById(Long id);
    List<CarDTO> findAllCar();
    void deleteCar(Long id);
    boolean isAvailable(CarDTO car, LocalDate startDate, LocalDate endDate);

}
