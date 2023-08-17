package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.repositories.PostOfficeRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

@Service
public class PostOfficeService extends CommonService<PostOffice, PostOfficeRepository> {
    public PostOfficeService(PostOfficeRepository repository) {
        super(repository);
    }
}
