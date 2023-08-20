package com.testcase.skyeng.services.modelServices;

import com.testcase.skyeng.exceptions.ResourceNotFoundException;
import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.repositories.MailPackageRepository;
import com.testcase.skyeng.services.CommonService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MailPackageService extends CommonService<MailPackage, MailPackageRepository> {
    public MailPackageService(MailPackageRepository repository) {
        super(repository);
    }

    public MailPackage checkItem(MailPackage mailPackage) throws RuntimeException {
        Long id = mailPackage.getId();
        Optional<MailPackage> findPackage = repository.findByReceiverAddressAndReceiver(mailPackage.getReceiverAddress(), mailPackage.getReceiver());
        if (findPackage.isPresent()) {
            if (findPackage.get().getId().equals(id)) {
                return findPackage.get();
            } else throw new ResourceNotFoundException("Mail package id mismatch with content!");
        } else throw new ResourceNotFoundException("Item is not found with id: " + id);
    }
}
