package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.models.Person;
import com.testcase.skyeng.services.modelServices.AddressService;
import com.testcase.skyeng.services.modelServices.MailPackageService;
import com.testcase.skyeng.services.modelServices.PersonService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailpackages")
public class MailPackageController extends CommonController<MailPackage, MailPackageService> {
    private final PersonService personService;
    private final AddressService addressService;

    protected MailPackageController(MailPackageService service, PersonService personService, AddressService addressService) {
        super(service);
        this.personService = personService;
        this.addressService = addressService;
    }


    @Override
    public MailPackage newItem(@RequestBody MailPackage newItem) {
        Address address = newItem.getReceiverAddress();
        if (address != null) {
            Address findAddress = addressService.findByAllFields(address);
            newItem.setReceiverAddress(findAddress);
        }
        Person receiver = newItem.getReceiver();

        if (receiver != null) {
            Person findPerson = personService.findByPassport(receiver);
            newItem.setReceiver(findPerson);
        }
        newItem.setReceiverIndex(address.getIndex());

        return super.newItem(newItem);
    }
}
