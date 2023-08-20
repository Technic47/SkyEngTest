package com.testcase.skyeng.repositories;

import com.testcase.skyeng.models.Address;
import com.testcase.skyeng.models.MailPackage;
import com.testcase.skyeng.models.Person;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailPackageRepository extends CommonRepository<MailPackage> {
    Optional<MailPackage> findByReceiverAddressAndReceiver(Address receiverAddress, Person receiver);
}
