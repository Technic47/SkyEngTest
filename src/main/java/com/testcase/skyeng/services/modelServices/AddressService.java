package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.repositories.AddressRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressService extends CommonService<Address, AddressRepository> {
    public AddressService(AddressRepository repository) {
        super(repository);
    }

    public Address findByAllFields(Address address){
        Optional<Address> findAddress = repository.findByCountryAndCityAndAddressLine1AndAddressLine2(
                address.getCountry(), address.getCity(), address.getAddressLine1(), address.getAddressLine2()
        );
        return findAddress.orElseGet(() -> saveItem(address));
    }
}
