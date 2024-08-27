package com.example.crud.controller.request;

import com.example.crud.controller.validator.Password;
import com.example.crud.controller.validator.Phone;
import com.example.crud.controller.validator.Username;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @NotBlank(message = "작성자는 필수 입력값입니다.")
        @Username
        String username,

        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        @Password
        String password,

        @NotBlank(message = "이메일은 필수 입력값입니다.")
        @Email(regexp = EMAIL_FORMAT)
        String email,

        @NotBlank(message = "휴대폰 번호는 필수 입력값입니다.")
        @Phone
        String phoneNumber
) {

    private static final String EMAIL_FORMAT = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

}
