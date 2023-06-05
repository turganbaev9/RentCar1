package kg.mega.RentCar.controller;

import kg.mega.RentCar.model.dto.DiscountDTO;
import kg.mega.RentCar.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {
    private  final DiscountService discountService;
    @PostMapping("saveDiscount")
    public DiscountDTO saveDiscount(@RequestBody DiscountDTO discount){
        return discountService.save(discount);
    }
    @GetMapping("/findById")
    public  DiscountDTO findById(Long id){
        DiscountDTO discountDTO=discountService.findById(id);
        return discountDTO;
    }
    @PutMapping("/update/{id}")
    public  ResponseEntity<DiscountDTO> update(@PathVariable("id") Long id,@RequestBody DiscountDTO discountDTO){
       DiscountDTO findDisc=discountService.findById(id);
       if (findDisc==null){
           return ResponseEntity.notFound().build();
       }
       discountDTO.setId(id);
       DiscountDTO update=discountService.update(discountDTO);
        return ResponseEntity.ok(update);

    }
    @DeleteMapping("/deleteDiscount/{id}")
    public ResponseEntity<Void> deleteDiscount(@PathVariable("id")Long id){
        DiscountDTO discountDTO=discountService.findById(id);
        if (discountDTO==null){
            return ResponseEntity.notFound().build();
        }else if (discountDTO!=null){discountService.deleteDiscount(id);
            ResponseEntity.ok(discountDTO);
        }else discountService.deleteDiscount(id);
        return ResponseEntity.noContent().build();
    }

@GetMapping("/findAllDiscount")
    public List<DiscountDTO>findAll(){
        return discountService.findAllDiscount();
}
}


