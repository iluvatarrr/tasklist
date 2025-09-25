package com.example.tasklist.web.mappers;

import com.example.tasklist.domain.user.User;
import com.example.tasklist.web.dto.user.UserDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-09-25T13:19:21+0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserDto d) {
        if ( d == null ) {
            return null;
        }

        User user = new User();

        user.setId( d.getId() );
        user.setName( d.getName() );
        user.setUsername( d.getUsername() );
        user.setPassword( d.getPassword() );
        user.setPasswordConfirmation( d.getPasswordConfirmation() );

        return user;
    }

    @Override
    public UserDto toDto(User e) {
        if ( e == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( e.getId() );
        userDto.setName( e.getName() );
        userDto.setUsername( e.getUsername() );
        userDto.setPassword( e.getPassword() );
        userDto.setPasswordConfirmation( e.getPasswordConfirmation() );

        return userDto;
    }

    @Override
    public List<UserDto> toDto(List<User> e) {
        if ( e == null ) {
            return null;
        }

        List<UserDto> list = new ArrayList<UserDto>( e.size() );
        for ( User user : e ) {
            list.add( toDto( user ) );
        }

        return list;
    }

    @Override
    public List<User> toEntity(List<UserDto> d) {
        if ( d == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( d.size() );
        for ( UserDto userDto : d ) {
            list.add( toEntity( userDto ) );
        }

        return list;
    }
}
