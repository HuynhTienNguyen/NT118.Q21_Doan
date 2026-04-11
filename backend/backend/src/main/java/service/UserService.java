package service;
import
import dto.user.request.CreateUserRequest;
import entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired //dependency injection
    private UserService userService;

    private User createUser(CreateUserRequest createUserRequest){
        User user = mapper.toEntity(createUserRequest);
    }
}
