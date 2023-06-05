package kg.mega.RentCar.controller;
import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.Order;
import kg.mega.RentCar.model.dto.CarDetailsDTO;
import kg.mega.RentCar.repository.CarRepo;
import kg.mega.RentCar.repository.OrderRepo;
import kg.mega.RentCar.service.CarDetetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/v1/carDetails")
@RequiredArgsConstructor
public class CarDetailsController {
    private final CarDetetailsService carDetetailsService;
    private  CarRepo carRepo;
    private  final OrderRepo orderRepo;


    @PostMapping("/saveCarDetails")
    public CarDetailsDTO saveCarDetails(@RequestBody CarDetailsDTO carDetailsDTO) {

            return  carDetetailsService.saveCarDetails(carDetailsDTO);
    }


    @GetMapping("/getCarDetails")
    public ResponseEntity<List<CarDetails>> getCarDetailsByReservedDates(@RequestParam("reservedDates") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate reservedDates) {
        List<CarDetails> carDetailsList = carDetetailsService.getCarDetailsByReservedDates(reservedDates);

        if (carDetailsList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(carDetailsList);
    }
}

