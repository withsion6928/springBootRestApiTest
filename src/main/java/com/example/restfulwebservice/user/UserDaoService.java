package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;
    static {
        users.add(new User(1, "Kenneth", new Date(), "pass1", "700001213-12132"));
        users.add(new User(2, "Alice", new Date(), "pass2", "70001213-1231"));
        users.add(new User(3, "Elena", new Date(), "pass3", "700001213-12132"));
    }

    public List<User> finaAll(){
        return users;
    }
    public User save(User user){
        if(user.getId() == null){
            user.setId((++userCount));

        }
        users.add(user);
        return user;
    }
    public User findOne(int id){
        for(User user: users){
            if(user.getId() == id)
                return user;
        }
        return null;
    }


    public User deleteByid(int id){
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext())
        {
            User user = iterator.next();
            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }

        return null;
    }

}
