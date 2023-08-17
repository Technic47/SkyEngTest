package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.repositories.AddressRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends CommonService<Address, AddressRepository> {
    public AddressService(AddressRepository repository) {
        super(repository);
    }
}
