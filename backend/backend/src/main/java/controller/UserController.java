package controller;

import dto.user.request.CreateUserRequest;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import respository.UserResponsitory;
import service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserResponsitory userResponsitory;
    @PostMapping

    public User createUser(@RequestBody CreateUserRequest createUserRequest){
        return userService.createUser(createUserRequest);
    }

}
