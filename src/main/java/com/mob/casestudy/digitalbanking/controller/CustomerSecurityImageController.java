package com.mob.casestudy.digitalbanking.controller;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.service.CustomerSecurityImagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-service")
public class CustomerSecurityImageController {

    CustomerSecurityImagesService customerSecurityImagesService;
    @Autowired
    public CustomerSecurityImageController(CustomerSecurityImagesService customerSecurityImagesService) {
        this.customerSecurityImagesService = customerSecurityImagesService;
    }

    @GetMapping("/client-api/v1/customer/{userName}")
    public ResponseEntity<CustomerSecurityImagesDto> retrieveCustomerSecurityImageByUsername(@PathVariable String userName) {
        return customerSecurityImagesService.retrieveCustomerSecurityImage(userName);
    }
}
