package kg.mega.RentCar.controller;

import kg.mega.RentCar.model.dto.OrderDTO;
import kg.mega.RentCar.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orderDetails")
public class OrderController {
    private final OrderService orderDetailsService;
    @PostMapping("/saveOrder")
    public OrderDTO saveOrder(@RequestBody OrderDTO orderDetailsDTO) {

        return orderDetailsService.save(orderDetailsDTO);
    }
    @GetMapping("/findById")
    public OrderDTO getOrder(Long id){
        OrderDTO dto=orderDetailsService.findById(id);
        return  dto;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<OrderDTO> update(@PathVariable("id") Long id,@RequestBody OrderDTO orderDetailsDTO){
        OrderDTO findOrd=orderDetailsService.findById(id);
        if (findOrd==null){
            return ResponseEntity.notFound().build();
        }
        orderDetailsDTO.setId(id);
        OrderDTO update=orderDetailsService.update(orderDetailsDTO);
        return ResponseEntity.ok(update);
    }
    @GetMapping("/findAllOrder")
    public List<OrderDTO>findAllOrder(){
        return orderDetailsService.findAllOrder();
    }
    @DeleteMapping("deleteOrder/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable ("id") Long id){
        OrderDTO dto=orderDetailsService.findById(id);
        if (dto==null){
            return ResponseEntity.notFound().build();
        } else if (dto!=null) {
            orderDetailsService.deleteOrder(id);
            ResponseEntity.ok(dto);
        }else orderDetailsService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }


}
