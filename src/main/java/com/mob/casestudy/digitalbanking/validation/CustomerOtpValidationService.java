package com.mob.casestudy.digitalbanking.validation;

import com.mob.casestudy.digitalbanking.dto.CustomerOtpDto;
import com.mob.casestudy.digitalbanking.entity.Customer;
import com.mob.casestudy.digitalbanking.entity.CustomerOtp;
import com.mob.casestudy.digitalbanking.exception.*;
import com.mob.casestudy.digitalbanking.repository.CustomerOtpRepository;
import com.mob.casestudy.digitalbanking.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class CustomerOtpValidationService {
    private final CustomerRepository customerRepository;
    private final CustomerOtpRepository customerOtpRepository;

    @Autowired
    public CustomerOtpValidationService(CustomerRepository customerRepository, CustomerOtpRepository customerOtpRepository) {
        this.customerRepository = customerRepository;
        this.customerOtpRepository = customerOtpRepository;
    }

    public void validateCustomerOtp(CustomerOtpDto customerOtpDto) {
        String userName = customerOtpDto.getUserName();
        String customerProvidedOtp = customerOtpDto.getOtp();
        Customer customer = customerRepository.findByUserName(userName);
        if (Objects.isNull(customer)) {
            throw new UserNotFoundException("The requested user is not found");
        }
        validateNullOrEmptyOtp(customerProvidedOtp);
        validateExpiredOtp(customer);
        validateInvalidOtpAndOtpAttempts(customer, customerProvidedOtp);
    }

    public void validateNullOrEmptyOtp(String customerProvidedOtp) {
        if (Objects.isNull(customerProvidedOtp) || customerProvidedOtp.isEmpty()) {
            throw new OtpEmptyOrNullException("Null or Empty OTP not acceptable");
        }
    }

    public void validateExpiredOtp(Customer customer) {
        CustomerOtp customerOtp = customer.getCustomerOtp();
        LocalDateTime expiryOn = customerOtp.getExpiresOn();
        LocalDateTime requestedTime = LocalDateTime.now();
        if (requestedTime.isAfter(expiryOn)) {
            throw new otpInitiatedExpiredException();
        }
    }

    public void validateInvalidOtpAndOtpAttempts(Customer customer, String customerProvidedOtp){
        CustomerOtp customerOtp = customer.getCustomerOtp();
        Integer otpRetries = customerOtp.getOptRetries();
        String existOtp = customerOtp.getOtp();
        if (otpRetries <= 2){
            if (!customerProvidedOtp.equals(existOtp)){
                customerOtp.setOptRetries(++otpRetries);
                customerOtpRepository.saveAndFlush(customerOtp);
                throw new OtpInvalidException();
            }
        } else {
            throw new OtpFailedAttemptException();
        }
    }
}
