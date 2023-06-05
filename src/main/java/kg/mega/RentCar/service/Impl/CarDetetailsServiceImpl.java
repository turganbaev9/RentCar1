package kg.mega.RentCar.service.Impl;
import kg.mega.RentCar.mapper.CarDetailsMapper;
import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.Order;
import kg.mega.RentCar.model.dto.CarDetailsDTO;
import kg.mega.RentCar.repository.CarDetailsRepo;
import kg.mega.RentCar.repository.CarRepo;
import kg.mega.RentCar.repository.OrderRepo;
import kg.mega.RentCar.service.CarDetetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;


@RequiredArgsConstructor
@Service
public class CarDetetailsServiceImpl implements CarDetetailsService {
    private  final CarDetailsRepo carDetailsRepo;
    private  final OrderRepo orderRepo;
   private final CarRepo carRepo;
    @Override
    public CarDetailsDTO saveCarDetails(CarDetailsDTO carDetailsDTO) {
        // Получаем идентификаторы машины и заказа
        Long carId = carDetailsDTO.getCar().getId();
        Long orderId = carDetailsDTO.getOrder().getId();

        // Проверяем, существуют ли машина и заказ с такими идентификаторами
        Car car = carRepo.findById(carId)
                .orElseThrow(() -> new RuntimeException("Машина с указанным идентификатором не найдена"));
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Заказ с указанным идентификатором не найден"));

        // Создаем объект CarDetails и устанавливаем значения машины, заказа и зарезервированных дат
        CarDetails carDetails = new CarDetails();
        carDetails.setCar(car);
        carDetails.setOrder(order);
        carDetails.setReservedDates(carDetailsDTO.getReservedDates());

        // Сохраняем объект CarDetails
        carDetailsRepo.save(carDetails);


        return CarDetailsMapper.INSTANCE.toDto(carDetails);
    }
    @Override
    public List<CarDetailsDTO> getAllOrders() {
        List<CarDetails> carDetailsList = carDetailsRepo.findAll();
        return CarDetailsMapper.INSTANCE.toDTOList(carDetailsList);
    }
    @Override
    public List<CarDetails> getCarDetailsByReservedDates(LocalDate reservedDates) {
        List<CarDetails> carDetailsList = carDetailsRepo.findByReservedDates(reservedDates);
        LocalDate reservedDatesList = orderRepo.findReservedDates();
        for (CarDetails carDetails : carDetailsList) {
            carDetails.setReservedDates(reservedDatesList);
        }
        return carDetailsList;
    }

}

