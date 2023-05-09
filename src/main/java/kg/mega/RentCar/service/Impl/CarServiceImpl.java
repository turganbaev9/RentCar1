package kg.mega.RentCar.service.Impl;

import kg.mega.RentCar.mapper.CarMapper;
import kg.mega.RentCar.mapper.OrderMapper;
import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.dto.CarDTO;
import kg.mega.RentCar.model.dto.OrderDTO;
import kg.mega.RentCar.repository.CarRepo;
import kg.mega.RentCar.repository.OrderRepo;
import kg.mega.RentCar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
@Service
@Transactional
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepo carRep;

    private  final OrderRepo orderRep;
    @Override
    public CarDTO save(CarDTO carDTO) {
        Car car = CarMapper.INSTANCE.toEntity(carDTO);
        Car save = carRep.save(car);
        return CarMapper.INSTANCE.toDto(save);
    }

    @Override
    public CarDTO update(CarDTO carDTO) {
        Car car = CarMapper.INSTANCE.toEntity(carDTO);
        Car update = carRep.findById(car.getId()).get();
        update.setModel(car.getModel());
        update.setColor(car.getColor());
        update.setCategory(car.getCategory());
        update.setPhoto(car.getPhoto());
        update.setEngineType(car.getEngineType());
        update.setEngineVolume(car.getEngineVolume());
        update.setMnfYear(car.getMnfYear());
        update.setGasPer100(car.getGasPer100());
        return CarMapper.INSTANCE.toDto(update);
    }

    @Override
    public CarDTO findById(Long id) {
        Car find = carRep.findById(id).get();

        return CarMapper.INSTANCE.toDto(find);
    }

    @Override
    public List<CarDTO> findAllCar() {
        List<Car> cars = carRep.findAll();
        if (cars == null) {
            return null;
        }
        return CarMapper.INSTANCE.toDTOList(cars);
    }

    @Override
    public void deleteCar(Long id) {
        carRep.deleteById(id);
    }

    @Override
    public boolean isAvailable(CarDTO car, LocalDate startDate, LocalDate endDate) {
        LocalDate now = LocalDate.now();
        List<OrderDTO> allOrders = OrderMapper.INSTANCE.toDTOList(orderRep.findAll());
        for (OrderDTO order : allOrders) {
            if (order.getCar().getId().equals(car.getId())) {
                if (order.getDateTimeFrom().isBefore(now) && order.getDateTimeTo().isAfter(now)) {
                    if ((startDate.isAfter(order.getDateTimeFrom()) && startDate.isBefore(order.getDateTimeTo()))
                            || (endDate.isAfter(order.getDateTimeFrom()) && endDate.isBefore(order.getDateTimeTo()))
                            || (startDate.isBefore(order.getDateTimeFrom()) && endDate.isAfter(order.getDateTimeTo()))) {
                        System.out.println("Машина занята в указанный период");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
