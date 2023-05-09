package kg.mega.RentCar.service.Impl;

import kg.mega.RentCar.mapper.CarDetailsMapper;
import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.dto.CarDetailsDTO;
import kg.mega.RentCar.repository.CarDetailsRepo;
import kg.mega.RentCar.service.CarDetetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CarDetetailsServiceImpl implements CarDetetailsService {
    private  final CarDetailsRepo carDetailsRepo;

    @Override
    public CarDetailsDTO save(CarDetailsDTO car) {
        CarDetails carDetails=CarDetailsMapper.INSTANCE.toEntity(car);
    CarDetails saved=carDetailsRepo.save(carDetails);
    return  CarDetailsMapper.INSTANCE.toDto(saved);
    }

    @Override
    public CarDetailsDTO findActiveCar(CarDetailsDTO carDetailsDTO, LocalDate reservedDates) {
        List<CarDetails> activeCars = carDetailsRepo.findByReservedDates(reservedDates);
        CarDetails activeCar = activeCars.get(0);
        return CarDetailsMapper.INSTANCE.toDto(activeCar);
    }
}
