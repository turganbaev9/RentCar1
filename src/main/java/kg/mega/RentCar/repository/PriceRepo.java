package kg.mega.RentCar.repository;

import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PriceRepo extends JpaRepository<Price,Long> {
    @Query("SELECT p FROM Price p WHERE p.endDate > CURRENT_DATE AND p.car.id = :carId")
    List<Price> findByEndDateAndCarId(@Param("carId") Long carId);

    Optional<Price> findByCar(CarDetails car);
}
