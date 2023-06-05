package kg.mega.RentCar.controller;

import kg.mega.RentCar.model.dto.AddressDTO;
import kg.mega.RentCar.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
    @RestController
    @RequestMapping("/api/v1/address")
    @RequiredArgsConstructor

public class AddressController {
        private final AddressService addressService;
        @PostMapping("/saveAddress")
        public AddressDTO saveAddress(@RequestBody AddressDTO address) {
            return addressService.saveAddress(address);
        }
        @PutMapping("/updateAddress/{id}")
        public ResponseEntity<AddressDTO> updateAddress(@PathVariable("id") Long id, @RequestBody AddressDTO addressDTO) {
            AddressDTO existingAddress = addressService.findById(id);
            if (existingAddress == null) {
                return ResponseEntity.notFound().build();
            }
            addressDTO.setId(id);

            AddressDTO updatedAddress = addressService.update(addressDTO);

            return ResponseEntity.ok(updatedAddress);
        }
        @DeleteMapping("/deleteAddress/{id}")
        public ResponseEntity<Void> deleteAddress(@PathVariable("id") Long id) {
            AddressDTO addressDTO = addressService.findById(id);
            if (addressDTO == null) {
                return ResponseEntity.notFound().build();
            }
            else if ( addressDTO!=null){
                addressService.deleteAddress(id);
                 ResponseEntity.ok(addressDTO);
            }else
                addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        }
        @GetMapping("/findAllAddress")
        public List<AddressDTO> findAllAddress(){
            return addressService.findAllAddress();
        }
        @GetMapping("/findById")
        public AddressDTO findById(Long id){
            AddressDTO addressDTO=addressService.findById(id);
            return addressDTO;
        }
    }

