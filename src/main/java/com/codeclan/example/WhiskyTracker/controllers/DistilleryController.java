package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository.WhiskyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/distilleries")
public class DistilleryController {

    @Autowired
    DistilleryRepository distilleryRepository;

    @Autowired
    WhiskyRepository whiskyRepository;

    @GetMapping
    public List<Distillery> getAllDistilleries(){
        return distilleryRepository.findAll();
    }

    @GetMapping(value = "/region/{region}")
    public List<Distillery>findDistilleryByRegion(@PathVariable String region){
     return distilleryRepository.findDistilleryByParticularRegion(region);

    }

    @GetMapping(value = "/{id}/whisky/age/{age}")
    public List<Whisky>findWhiskiesFromDistilleryByAge(@PathVariable Long id,  int age){
        return whiskyRepository.findWhiskiesFromDistilleryByAge(id, age);
    }

    @GetMapping(value = "/Whiskies/age/{age}")
    public List<Distillery>findDistilleryThatHaveOldWhiskies(@PathVariable int age){
        return distilleryRepository.findDistilleriesThatHaveOldWhiskies(age);
    }


}
