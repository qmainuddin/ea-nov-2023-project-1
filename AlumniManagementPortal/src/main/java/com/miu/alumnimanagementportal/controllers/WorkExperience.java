package com.miu.alumnimanagementportal.controllers;

import com.miu.alumnimanagementportal.common.Converter;
import com.miu.alumnimanagementportal.dtos.WorkExperienceDto;
import com.miu.alumnimanagementportal.services.WorkExperienceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/news")
public class WorkExperience {
    private final WorkExperienceService workExperienceService;
    private final Converter converter;

    @PostMapping
    public ResponseEntity<?> createWorkExperience(@Valid @RequestBody WorkExperienceDto workExperienceDto) {
        workExperienceService.create(workExperienceDto);
        return converter.buildReposeEntity(Map.of("message", "Work Experience created successfully"), HttpStatus.ACCEPTED);
    }

  //need all experience for single user

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorkExperienceById(@PathVariable Long id) {
        return converter.buildReposeEntity(Map.of("data", workExperienceService.getWorkExperienceById(id)), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateWorkExperienceById( @PathVariable Long id,@Valid @RequestBody WorkExperienceDto workExperienceDto) {
        return converter.buildReposeEntity(Map.of("data", workExperienceService.update(workExperienceDto,id)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkExperienceById(@PathVariable Long id) {
        workExperienceService.delete(id);
        return converter.buildReposeEntity(Map.of("message", "Work Experience Deleted successfully"), HttpStatus.ACCEPTED);
    }




}
