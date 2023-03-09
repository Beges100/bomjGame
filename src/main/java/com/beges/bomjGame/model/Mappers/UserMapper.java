package com.beges.bomjGame.model.Mappers;


import com.beges.bomjGame.model.DTO.ChatUserDTO;
import com.beges.bomjGame.model.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "chat_id", target = "id")
    @Mapping(source = "nickName", target = "userName")
    @Mapping(source = "name", target = "firstName")
    ChatUserDTO toUserDTO(User user);

    @InheritInverseConfiguration
    User toUser(ChatUserDTO userDTO);
}
