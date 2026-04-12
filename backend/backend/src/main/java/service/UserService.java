package service;
import dto.user.request.CreateUserRequest;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import Mapper.UserMapper;
import respository.UserResponsitory;

import java.util.List;

@Service
@RequiredArgsConstructor
@Mapper
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserResponsitory userResponsitory;

    public User createUser(CreateUserRequest createUserRequest){
        User user = userMapper.toCrete(createUserRequest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public List<User> findAllUser(){
        return userResponsitory.findAll();
    }

}
