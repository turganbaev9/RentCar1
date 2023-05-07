package kg.mega.RentCar.service;

import kg.mega.RentCar.model.dto.CarDetailsDTO;


import java.time.LocalDate;


public interface CarDetetailsService {
    CarDetailsDTO save(CarDetailsDTO car);
    CarDetailsDTO update(CarDetailsDTO carDTO);
    CarDetailsDTO findActiveCar(CarDetailsDTO carDetailsDTO,LocalDate reservedDates);
}
