package kg.mega.RentCar.repository;

import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepo extends JpaRepository<Discount,Long> {
    Optional<Discount> findByCar(Car car);
}
