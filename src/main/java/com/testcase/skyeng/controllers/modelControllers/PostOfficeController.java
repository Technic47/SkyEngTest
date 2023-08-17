package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.services.modelServices.AddressService;
import com.testcase.skyeng.services.modelServices.PostOfficeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postoffices")
public class PostOfficeController extends CommonController<PostOffice, PostOfficeService> {
    private final AddressService addressService;
    protected PostOfficeController(PostOfficeService service, AddressService addressService) {
        super(service);
        this.addressService = addressService;
    }

    @Override
    public PostOffice newItem(@RequestBody PostOffice newItem) {
        Address address = newItem.getAddress();
        if (address != null) {
            Address findAddress = addressService.findByAllFields(address);
            newItem.setAddress(findAddress);
        }
        return super.newItem(newItem);
    }
}
