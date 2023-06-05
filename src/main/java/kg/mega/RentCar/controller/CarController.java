package kg.mega.RentCar.controller;

import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.dto.CarDTO;
import kg.mega.RentCar.repository.CarRepo;
import kg.mega.RentCar.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/car")
@RestController
public class CarController {

    private final CarService carService;

    @PostMapping("/saveCar")
    public CarDTO saveСar(CarDTO car,@RequestPart MultipartFile file) {
        return carService.save(car,file);
    }

    @GetMapping("/findById")
    public CarDTO findById(Long id) {
        CarDTO carDTO = carService.findById(id);
        return carDTO;
    }

    @PutMapping("/updateCar/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable("id") Long id, @RequestBody CarDTO carDTO) {
        CarDTO existingCar = carService.findById(id);
        if (existingCar == null) {
            return ResponseEntity.notFound().build();
        }
        carDTO.setId(id);
        CarDTO updateCar = carService.update(carDTO);

        return ResponseEntity.ok(updateCar);
    }

    @DeleteMapping("/deleteCar/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
        CarDTO carDTO = carService.findById(id);
        if (carDTO == null) {
            return ResponseEntity.notFound().build();
        } else if (carDTO != null) {carService.deleteCar(id);
            ResponseEntity.ok(carDTO);
        } else
            carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findAllCar")
    public List<CarDTO> findAllCar() {
        return carService.findAllCar();
    }


    @GetMapping("findAvailableCar")
    public List<CarDTO> findAvailbleCar(
                                        @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                        @RequestParam("endDate")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)LocalDate endDate) {
List<CarDTO>allCarList=carService.findAllCar();
List<CarDTO>availableCar=new ArrayList<>();
        for (CarDTO car : allCarList) {
            boolean isavailable=carService.isAvailable(car,startDate,endDate);
        if (isavailable){
            availableCar.add(car);
        }
        }
            return availableCar;
        }





}

