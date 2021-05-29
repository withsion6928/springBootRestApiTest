package com.example.restfulwebservice.user;

import org.springframework.core.io.Resource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
class UserContoller {
    private UserDaoService service;
    public UserContoller(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllusers(){
        return service.finaAll();
    }
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user =service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("Id[%s] not found", id));

        }

//        Resource<User> resource = new Resource<>(user);
//        ControllerLinkBuil linkTo = linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers()));
//        resource.add(linkTo.withRel("all-users");

        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllusers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }


    @PostMapping("/users")
    public ResponseEntity<User> createUsers(@RequestBody User user)
    {
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }


    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable int id)
    {
        User user = service.deleteByid(id);
        if(user == null){
            throw new UserNotFoundException(String.format("Id[%s] not found", id));

        }
    }
}
