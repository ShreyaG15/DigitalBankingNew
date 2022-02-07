package com.mob.casestudy.digitalbanking.entity;

import com.mob.casestudy.digitalbanking.dto.CustomerSecurityImagesDto;
import com.mob.casestudy.digitalbanking.entity.embeddable.CustomerSecurityImagesId;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class CustomerSecurityImages {

    @EmbeddedId
    private CustomerSecurityImagesId customerSecurityImagesId = new CustomerSecurityImagesId();

    @Column(length = 50)
    private String securityImageCaption;

    @Column(length = 50)
    private String createdOn;

    @OneToOne
    @MapsId("customerId")
    private Customer customer;

    @OneToOne
    @MapsId("securityImageId")
    private SecurityImages securityImages;

    public CustomerSecurityImagesDto toDto(){
        return new CustomerSecurityImagesDto(customerSecurityImagesId.getSecurityImageId(),securityImages.getSecurityImageName(),
                securityImageCaption,securityImages.getSecurityImageUrl());
    }
}