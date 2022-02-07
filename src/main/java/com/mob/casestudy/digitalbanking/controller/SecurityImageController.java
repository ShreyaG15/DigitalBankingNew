package com.mob.casestudy.digitalbanking.controller;


import com.mob.casestudy.digitalbanking.dto.SecurityImagesDto;
import com.mob.casestudy.digitalbanking.dto.SecurityImagesList;
import com.mob.casestudy.digitalbanking.entity.SecurityImages;
import com.mob.casestudy.digitalbanking.exception.ImageNotFoundException;
import com.mob.casestudy.digitalbanking.repository.SecurityImageRepository;
import com.mob.casestudy.digitalbanking.service.SecurityImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer-service")
public class SecurityImageController {

    @Autowired
    public SecurityImageService securityImageService;
    @Autowired
    public SecurityImageRepository securityImageRepository;



    @GetMapping("/service-api/v2/securityImages")
    public SecurityImagesList getList() {
        List<SecurityImages> list = securityImageService.findAll();
        SecurityImagesList securityImages = new SecurityImagesList();
        if (list==null||list.isEmpty()) {
            throw new ImageNotFoundException();
        }
        for (SecurityImages securityImages1:list){
            securityImages.addSecurityImages(securityImages1.toDto());
        }
        return securityImages;
    }

    @PostMapping("/service-api/v2/securityImages")
    public void createSecurityImage(@RequestBody SecurityImagesDto securityImages) {
    securityImageRepository.save(securityImages.toEntity());
    }
}
