package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.Person;
import com.testcase.skyeng.services.modelServices.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/persons")
public class PersonController extends CommonController<Person, PersonService> {

    protected PersonController(PersonService service) {
        super(service);
    }
}
