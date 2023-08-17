package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.services.modelServices.AddressService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/addresses")
public class AddressController extends CommonController<Address, AddressService> {
    protected AddressController(AddressService service) {
        super(service);
    }
}
