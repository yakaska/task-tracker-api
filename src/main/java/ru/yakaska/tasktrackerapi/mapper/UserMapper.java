package ru.yakaska.tasktrackerapi.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import ru.yakaska.tasktrackerapi.model.User;
import ru.yakaska.tasktrackerapi.payload.response.UserResponse;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = UserQualifier.class)
public interface UserMapper {
    @Mapping(source = "userRole", target = "roles", qualifiedByName = "mapRoles")
    UserResponse toUserResponse(User user);
}
