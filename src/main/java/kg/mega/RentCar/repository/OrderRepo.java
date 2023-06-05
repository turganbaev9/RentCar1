package kg.mega.RentCar.repository;
import kg.mega.RentCar.model.CarDetails;
import kg.mega.RentCar.model.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    @Query("SELECT DISTINCT o.dateTimeTo FROM Order o")
    LocalDate findReservedDates();




}
