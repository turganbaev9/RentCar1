package kg.mega.RentCar.repository;

import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarDetailsRepo extends JpaRepository<CarDetails,Long> {

    List<CarDetails> findByReservedDates(LocalDate reservedDates);
}
