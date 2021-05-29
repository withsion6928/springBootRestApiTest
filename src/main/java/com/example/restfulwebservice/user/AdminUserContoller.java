package com.example.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/admin")
@RestController
class AdminUserContoller {
    private UserDaoService service;
    public AdminUserContoller(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllusers(){
        List<User> users = service.finaAll();

        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","password");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);

        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;

    }
    @GetMapping("/users/{id}")
    public MappingJacksonValue retrieveUser(@PathVariable int id){
        User user =service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("Id[%s] not found", id));

        }
        SimpleBeanPropertyFilter filter =  SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate","ssn");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo",filter);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);

        mappingJacksonValue.setFilters(filterProvider);

        return mappingJacksonValue;

    }


}
