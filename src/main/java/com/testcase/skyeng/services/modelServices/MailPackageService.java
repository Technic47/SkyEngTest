package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.repositories.MailPackageRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

@Service
public class MailPackageService extends CommonService<MailPackage, MailPackageRepository> {
    public MailPackageService(MailPackageRepository repository) {
        super(repository);
    }
}
