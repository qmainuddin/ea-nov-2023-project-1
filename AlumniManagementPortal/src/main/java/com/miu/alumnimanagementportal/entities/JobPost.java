package com.miu.alumnimanagementportal.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobPost extends BaseEntity {
    @Id
    private Long id;

    private String title;
    private String description;

    //enum - parttime, full-time, contract, remote
    private JobType jobType;

    //owner of the job
    @OneToOne
    private User owner;

    private boolean isPublished = false;

    //status of the job - open or closed
    private String status;


}
