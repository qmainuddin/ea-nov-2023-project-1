package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Profile extends BaseEntity {
    @OneToOne(mappedBy = "profile", optional = false, orphanRemoval = true)
    private User user;
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    private String phone;
    private String profileImage;

}
