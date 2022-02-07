package com.mob.casestudy.digitalbanking.dto;

import java.util.ArrayList;
import java.util.List;

public class SecurityImagesList {


    private List<SecurityImagesDto> securityImages = new ArrayList<>();

    public List<SecurityImagesDto> getSecurityImages() {
        return securityImages;
    }

    public void addSecurityImages(SecurityImagesDto securityImages) {
        this.securityImages.add(securityImages);
    }

}
