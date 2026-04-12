package Mapper;

import dto.user.request.CreateUserRequest;
import entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toCrete(CreateUserRequest createUserRequest);
}
