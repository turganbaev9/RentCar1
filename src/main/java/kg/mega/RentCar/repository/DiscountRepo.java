package kg.mega.RentCar.repository;
import kg.mega.RentCar.model.Discount;
import kg.mega.RentCar.model.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountRepo extends JpaRepository<Discount,Long> {

 @Query(value = "select *\n" +
          "from tb_discount where car_id= 1 and end_date <=now() and count_day <=17\n" +
          "order by count_day desc\n" +
          "limit 1",nativeQuery = true)
Optional<Discount >findActiveByCarAndCountDay(@Param("carId") Long carId, Integer countDay);
}
