package com.example.selabboard.model.dto;

import com.example.selabboard.model.entity.Member;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginMember {
    @NotBlank
    @Email
    @Size(min = 5, max = 30, message = "사용자 아이디는 이메일 형식이며 5~30자이어야 합니다.")
    private String userId;

    @NotBlank
    @Size(min = 2, max = 20, message = "비밀번호는 2~20자이며 영문과 숫자만 허용합니다.")
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String password;

}
