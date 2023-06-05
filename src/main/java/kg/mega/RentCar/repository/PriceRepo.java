package kg.mega.RentCar.repository;
import kg.mega.RentCar.model.Car;
import kg.mega.RentCar.model.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PriceRepo extends JpaRepository<Price,Long> {
    Optional<Price> findByCar(Car car);
}
