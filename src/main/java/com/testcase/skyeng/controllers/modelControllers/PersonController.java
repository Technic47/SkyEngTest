package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.Person;
import com.testcase.skyeng.services.modelServices.AddressService;
import com.testcase.skyeng.services.modelServices.PersonService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
public class PersonController extends CommonController<Person, PersonService> {
    private final AddressService addressService;

    protected PersonController(PersonService service, AddressService addressService) {
        super(service);
        this.addressService = addressService;
    }


    @PostMapping("/{personId}/addAddress/{addressId}")
    public Person addAddressById(@PathVariable Long personId,
                             @PathVariable Long addressId){
        Address addressToAdd = addressService.getById(addressId);
        return service.addAddress(personId, addressToAdd);
    }

    @Override
    public Person newItem(@RequestBody Person newItem) {
        Address address = newItem.getAddress();
        if (address != null) {
            Address findAddress = addressService.checkExist(address);
            newItem.setAddress(findAddress);
        }
        return super.newItem(newItem);
    }
}
