package com.example.selabboard.model.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class JoinMember {
    @NotBlank
    @Email
    @Size(min = 5, max = 30, message = "사용자 아이디는 이메일 형식이며 5~30자이어야 합니다.")
    private String userId;

    @NotBlank
    @Size(min = 2, max = 20, message = "비밀번호는 2~20자이어야 합니다.")
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "비밀번호는 영문, 숫자만 허용합니다.")
    private String password;

    @NotBlank
    @Size(min=2, max=6, message = "이름은 2~6자이어야 합니다.")
    @Pattern(regexp = "[가-힣]*", message = "한글만 입력 가능합니다.")
    private String name;

    @NotBlank
    private String address;

}
