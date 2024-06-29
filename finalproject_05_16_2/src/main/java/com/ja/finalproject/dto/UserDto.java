package com.ja.finalproject.dto;

import lombok.Data;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserDto {
    private int id;
    private String account_name;
    private String password;
    private String nickname;
    private String email;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    private String phone;
    private Date created_at;
}
