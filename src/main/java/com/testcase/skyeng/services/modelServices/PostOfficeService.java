package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.exceptions.ResourceNotFoundException;
import com.testcase.skyeng.models.PostOffice;
import com.testcase.skyeng.repositories.PostOfficeRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostOfficeService extends CommonService<PostOffice, PostOfficeRepository> {
    public PostOfficeService(PostOfficeRepository repository) {
        super(repository);
    }

    public PostOffice checkItem(PostOffice postOffice) throws RuntimeException {
        Long id = postOffice.getId();
        Optional<PostOffice> findPackage = repository.findByNameAndIndex(postOffice.getName(), postOffice.getIndex());
        if (findPackage.isPresent()) {
            if (findPackage.get().getId().equals(id)) {
                return findPackage.get();
            } else throw new ResourceNotFoundException("Post office id mismatch with content!");
        } else throw new ResourceNotFoundException("Item is not found with id: " + id);
    }
}
