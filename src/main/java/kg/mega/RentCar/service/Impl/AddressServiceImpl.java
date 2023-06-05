package kg.mega.RentCar.service.Impl;

import kg.mega.RentCar.mapper.AddressMapper;
import kg.mega.RentCar.model.Address;
import kg.mega.RentCar.model.dto.AddressDTO;
import kg.mega.RentCar.repository.AddressRepo;
import kg.mega.RentCar.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
  private final   AddressRepo addressRepo;
    @Override
    public AddressDTO saveAddress(AddressDTO addressDTO) {
        Address address=AddressMapper.INSTANCE.toEntity(addressDTO);

addressRepo.save(address);
        return AddressMapper.INSTANCE.toDto(address);
    }

    @Override
    public AddressDTO update(AddressDTO addressDTO) {
        Address address=AddressMapper.INSTANCE.toEntity(addressDTO);
        Address updateAddress=addressRepo.findById(address.getId()).get();
        updateAddress.setCity(address.getCity());
        updateAddress.setStreet(address.getStreet());
        updateAddress.setBuildingNum(address.getBuildingNum());
        return AddressMapper.INSTANCE.toDto(updateAddress);
    }

    @Override
    public AddressDTO findById(Long id) {
        Optional<Address> addressOptional = addressRepo.findById(id);
        Address found = addressOptional.orElse(null);
        return AddressMapper.INSTANCE.toDto(found);
    }


    @Override
    public List<AddressDTO> findAllAddress() {
        List<Address> addressList=addressRepo.findAll();
        if (addressList==null){
            return null;
        }
        return AddressMapper.INSTANCE.toDTOList(addressList);
    }

    @Override
    public void deleteAddress(Long id) {
addressRepo.deleteById(id);
    }
}
