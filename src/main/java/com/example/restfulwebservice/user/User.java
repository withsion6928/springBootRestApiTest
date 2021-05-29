package com.example.restfulwebservice.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
//user git Test2
@Data
@AllArgsConstructor
public class User {
    private Integer id;
    private String name;
    private Date joinDate;
 }
