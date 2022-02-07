package com.mob.casestudy.digitalbanking.service;

import com.mob.casestudy.digitalbanking.entity.Customer;
import com.mob.casestudy.digitalbanking.entity.enumerator.Language;
import com.mob.casestudy.digitalbanking.entity.enumerator.UserStatus;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer() {
        Customer customerBuilder = Customer.builder().userName("Sg").
                firstName("Shreya").lastName("Gandhi").phoneNumber("1234567891").
                email("sgandhi@gmail.com").status(UserStatus.ACTIVE).
                preferredLanguage(Language.EN).build();
        return customerRepository.save(customerBuilder);
    }
}
