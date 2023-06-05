package kg.mega.RentCar.service.Impl;
import kg.mega.RentCar.mapper.OrderMapper;
import kg.mega.RentCar.model.*;
import kg.mega.RentCar.model.dto.*;
import kg.mega.RentCar.repository.*;
import kg.mega.RentCar.service.CarService;
import kg.mega.RentCar.service.DiscountService;
import kg.mega.RentCar.service.OrderService;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRep;
    private final DiscountService discountService;
    private final CarRepo carRep;
    private final AddressRepo addressRep;
    private final DiscountRepo discountRep;
    private final PriceRepo priceRep;
    private final CarService carService;

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);

        // Получаем объекты Car, Address и ReturnAddress по id
        Car car = carRep.findById(orderDTO.getCar().getId())
                .orElseThrow(() -> new NoSuchElementException("Car not found"));
        Address address = addressRep.findById(orderDTO.getAddress().getId())
                .orElseThrow(() -> new NoSuchElementException("Address not found"));
        Address returnAddress = addressRep.findById(orderDTO.getReturnAddress().getId())
                .orElseThrow(() -> new NoSuchElementException("Return address not found"));
        order.setCar(car);
        order.setAddress(address);
        order.setReturnAddress(returnAddress);
        LocalDate startDate = orderDTO.getDateTimeFrom();
        LocalDate endDate = orderDTO.getDateTimeTo();
        // Проверяется доступность автомобиля
        if (!carService.isAvailable(orderDTO.getCar(),startDate,endDate)) {

            return null;
        }

        long duration = ChronoUnit.DAYS.between(startDate,endDate);
        // Нахожу цену для автомобиля
        Price price = priceRep.findByCar(car)
                .orElseThrow(() -> new NoSuchElementException("Price not found for car " + car.getId()));
        Double carPrice = price.getPrice();
        Double beforeTotalPrice = carPrice * duration;
        order.setPrice(price);
        order.setPriceBeforeDiscount(beforeTotalPrice);
        // Расчет скидки
        Discount calculatedDiscount = discountService.findActive(car, (int) duration);
        Double totalDiscount = calculatedDiscount.getDiscount();
        order.setDiscount(calculatedDiscount);

        // Расчет цены с учетом скидки
        Double totalPrice = beforeTotalPrice - (beforeTotalPrice * totalDiscount / 100);
        order.setPriceWithDiscount(beforeTotalPrice);
        order.setPriceWithDiscount(totalPrice);

        sendMail(order.getClientEmail(),"Your invoice saved"+totalPrice.doubleValue(),toString());
        Order savedOrder = orderRep.save(order);
        return OrderMapper.INSTANCE.toDto(savedOrder);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {
        Order order = OrderMapper.INSTANCE.toEntity(orderDTO);
        Order updateOrder = orderRep.findById(order.getId()).get();
        updateOrder.setBabySeat(order.isBabySeat());
        updateOrder.setWithDriver(order.isWithDriver());
        updateOrder.setClientName(order.getClientName());
        updateOrder.setClientEmail(order.getClientEmail());
        updateOrder.setClientPhone(order.getClientPhone());
        updateOrder.setDateTimeFrom(order.getDateTimeFrom());
        updateOrder.setDateTimeTo(order.getDateTimeTo());
        return OrderMapper.INSTANCE.toDto(updateOrder);
    }

    @Override
    public List<OrderDTO> findAllOrder() {
        List<Order>orders=orderRep.findAll();
        return OrderMapper.INSTANCE.toDTOList(orders);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRep.deleteById(id);
    }

    @Override
    public OrderDTO findById(Long id) {
        return OrderMapper.INSTANCE.toDto(orderRep.findById(id).get());
    }

    private void sendMail(String to, String subject, String text) {
        OkHttpClient client = new OkHttpClient();
        RequestBody body=RequestBody.create("null".getBytes());
        HttpUrl url = HttpUrl.parse("http://localhost:8080/api/v1/mail/sendMail").newBuilder()
                .addQueryParameter("to", to).
                addQueryParameter("subject", subject).
                addQueryParameter("text", text).build();

        Request request = new Request.Builder().
                url(String.format("http://localhost:8080/api/v1/mail/sendMail?to=%s&subject=%s&text=%s",to,subject,text))
       // url(url)
                .post(body)
                .build();
        try {
            client.newCall(request).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}