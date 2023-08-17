package com.testcase.skyeng.repositories;

import com.testcase.skyeng.models.Address;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends CommonRepository<Address> {
    Optional<Address> findByCountryAndCityAndAddressLine1AndAddressLine2(
            String country,
            String city,
            String addressLine1,
            String addressLine2);
}
