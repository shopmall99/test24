package com.ja.finalproject.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ja.finalproject.dto.UserDto;

@Mapper
public interface UserSqlMapper {
    public void createUser(UserDto userDto);

    //select 쿼리 리턴 타입이 단일 객체(not list)인 경우, 쿼리 결과가 없을 시에 정상적으로 null값을 리턴함
    public UserDto findUserByAccountNameAndPassword(UserDto userDto);

    public UserDto findUserById(int id);

}
