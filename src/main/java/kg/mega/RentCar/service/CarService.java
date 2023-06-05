package kg.mega.RentCar.service;

import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.dto.CarDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface CarService {
    CarDTO save(CarDTO car, MultipartFile file);
    CarDTO update(CarDTO carDTO);
    CarDTO findById(Long id);
    List<CarDTO> findAllCar();
    void deleteCar(Long id);
    boolean isAvailable(CarDTO car, LocalDate startDate, LocalDate endDate);

}
