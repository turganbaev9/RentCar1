package kg.mega.RentCar.service.Impl;

import kg.mega.RentCar.mapper.CarDetailsMapper;
import kg.mega.RentCar.mapper.CarMapper;
import kg.mega.RentCar.mapper.OrderMapper;
import kg.mega.RentCar.model.*;
import kg.mega.RentCar.model.dto.DiscountDTO;
import kg.mega.RentCar.model.dto.OrderDTO;
import kg.mega.RentCar.repository.*;
import kg.mega.RentCar.service.CarService;
import kg.mega.RentCar.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.temporal.ChronoUnit;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRep;
    private final CarRepo carRep;
    private  final AddressRepo addressRep;
    private final DiscountRepo discountRep;
    private final PriceRepo priceRep;
    private  final CarService carService;
    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        if (orderDTO.getDiscount() != null) {
            Long discountId = orderDTO.getDiscount().getId();
            Discount discount = discountRep.findById(discountId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid discount id"));
            order.setDiscount(discount);
        }
        calcDiscount(orderDTO);
        Car car = carRep.findById(orderDTO.getCar().getId()).get();
        order.setCar(car);
        carService.isAvailable(CarMapper.INSTANCE.toDto(car),orderDTO.getDateTimeFrom(),orderDTO.getDateTimeTo());
        Address address = addressRep.findById(orderDTO.getAddress().getId()).get();
        order.setAddress(address);
        Discount discount = order.getDiscount();
        if (orderDTO.getDateTimeFrom()!=null && orderDTO.getDateTimeTo()!=null){
            long countday = ChronoUnit.DAYS.between(orderDTO.getDateTimeFrom(), orderDTO.getDateTimeTo());
            if (discount != null) {
                discount.setCountDay((int) countday);
            } }
        Order savedOrder = orderRep.save(order);
        return OrderMapper.INSTANCE.toDto(savedOrder);
    }
    private void calcDiscount( OrderDTO orderDetails) {
        DiscountDTO discount = orderDetails.getDiscount();
        if (discount != null) {
            Long discountId = discount.getId();
            Discount entityDiscount = discountRep.findById(discountId).orElseThrow(() -> new EntityNotFoundException("Discount with id " + discountId + " not found"));
            double priceBeforeDiscount= orderDetails.getPrice().getPrice();
            Price price = priceRep.findByCar(CarMapper.INSTANCE.toEntity(orderDetails.getCar())).orElseThrow(() -> new EntityNotFoundException("Price not found for car " + orderDetails.getCar().getId()));
            Double discountAmount = priceBeforeDiscount * entityDiscount.getDiscount() / 100.0;
            Double priceWithDiscount = priceBeforeDiscount - discountAmount;
            orderDetails.setPriceBeforeDiscount(priceBeforeDiscount);
            orderDetails.setPriceWithDiscount(priceWithDiscount);
        }
    }
    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        Order updateOrder = orderRep.findById(order.getId()).get();
        updateOrder.setCar(order.getCar());
        updateOrder.setBabySeat(order.isBabySeat());
        updateOrder.setWithDriver(order.isWithDriver());
        updateOrder.setClientName(order.getClientName());
        updateOrder.setClientEmail(order.getClientEmail());
        updateOrder.setClientPhone(order.getClientPhone());
        updateOrder.setDateTimeFrom(order.getDateTimeFrom());
        updateOrder.setDateTimeTo(order.getDateTimeTo());
        updateOrder.setPriceBeforeDiscount(order.getPriceWithDiscount());
        return OrderMapper.INSTANCE.toDto(updateOrder);
    }

    @Override
    public List<OrderDTO> findAllOrder() {
        return OrderMapper.INSTANCE.toDTOList(orderRep.findAll());    }

    @Override
    public void deleteOrder(Long id) {
        orderRep.deleteById(id);
    }

    @Override
    public OrderDTO findById(Long id) {
        return OrderMapper.INSTANCE.toDto(orderRep.findById(id).get());
    }
}
