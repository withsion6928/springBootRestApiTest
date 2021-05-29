package com.example.restfulwebservice.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public User retrieveUser(@PathVariable int id){
        User user =service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("Id[%s] not found", id));

        }

        return user;
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
