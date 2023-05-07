package kg.mega.RentCar.mapper;
import kg.mega.RentCar.model.Order;
import kg.mega.RentCar.model.dto.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;
@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE= Mappers.getMapper(OrderMapper.class);
    Order toEntity(OrderDTO orderDTO);
    OrderDTO toDto(Order order);
    List<Order> toEntityList(List<OrderDTO>orderDTOList);
    List<OrderDTO>toDTOList(List<Order >orderList);
}
