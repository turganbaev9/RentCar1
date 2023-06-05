package kg.mega.RentCar.service;

import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.dto.AddressDTO;
import kg.mega.RentCar.model.dto.CarDetailsDTO;


import java.time.LocalDate;
import java.util.List;


public interface CarDetetailsService {
    CarDetailsDTO saveCarDetails( CarDetailsDTO carDetailsDTO);
    List<CarDetailsDTO> getAllOrders();
List<CarDetails> getCarDetailsByReservedDates(LocalDate reservedDates);




}
