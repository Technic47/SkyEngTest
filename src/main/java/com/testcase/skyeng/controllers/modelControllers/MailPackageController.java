package com.testcase.skyeng.controllers.modelControllers;

import com.testcase.skyeng.controllers.CommonController;
import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.services.modelServices.MailPackageService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mailpackages")
public class MailPackageController extends CommonController<MailPackage, MailPackageService> {
    private final MailPackageService service;
    protected MailPackageController(MailPackageService service) {
        super(service);
        this.service = service;
    }


}
