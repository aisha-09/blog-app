package com.aisha.blogapp.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotEmpty
    @Size(min=4,message="Username must be at least 4 characters long")
    private String name;

    @Email(message="Email not valid!!")
    private String email;

    @NotEmpty //checks both null and empty
    @Size(min=3, max=10, message="Password must be at least 3 characters and at max 10 characters")
    private String password;

    @NotEmpty
    private String about;
}
