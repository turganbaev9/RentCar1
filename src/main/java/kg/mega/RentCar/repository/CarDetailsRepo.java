package kg.mega.RentCar.repository;

import kg.mega.RentCar.model.CarDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDetailsRepo extends JpaRepository<CarDetails,Long> {
}
