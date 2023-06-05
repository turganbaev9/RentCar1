package kg.mega.RentCar.controller;

import kg.mega.RentCar.model.dto.PriceDTO;
import kg.mega.RentCar.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/price")
public class PriceController {
    private final PriceService priceService;

    @PostMapping("/savePrice")
    public PriceDTO savePrice(@RequestBody PriceDTO priceDTO) {
        PriceDTO save = priceService.save(priceDTO);
        return save;
    }

    @GetMapping("/findById")
    public PriceDTO getPrice(Long id) {
        PriceDTO dto = priceService.finById(id);
        return dto;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity< PriceDTO> update(@PathVariable ("id") Long id,@RequestBody PriceDTO priceDTO) {
        PriceDTO findpr = priceService.finById(id);
        if (findpr == null) {
            return ResponseEntity.notFound().build();
        }
        priceDTO.setId(id);
        PriceDTO updatePrice = priceService.update(priceDTO);
        return ResponseEntity.ok(updatePrice);
    }
    @GetMapping("/findAll")
    public List<PriceDTO>findAll(){
        return priceService.findAll();

    }
    @DeleteMapping("/deletePrice/{id}")
    public ResponseEntity<Void> deletePrice(@PathVariable("id") Long id) {
        PriceDTO dto = priceService.finById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        } else if (dto != null) {
            priceService.deletePrice(id);
            ResponseEntity.ok(dto);

        }else priceService.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
}
