package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.services.modelServices.PostOfficeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postoffices")
public class PostOfficeController extends CommonController<PostOffice, PostOfficeService> {
    protected PostOfficeController(PostOfficeService service) {
        super(service);
    }
}
