package com.kuropatin.bookingapp.model.request;

import com.kuropatin.bookingapp.model.Gender;
import com.kuropatin.bookingapp.validation.AgeXPlus;
import com.kuropatin.bookingapp.validation.ValueOfEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserCreateRequest {

    @NotBlank(message = "Enter login")
    @Size(min = 2, max = 20, message = "Login should be between 2 and 20 characters")
    private String login;

    @NotBlank(message = "Enter password")
    @Size(min = 8, max = 20, message = "Password should be between 8 and 20 characters")
    private String password;

    @NotBlank(message = "Enter name")
    @Size(min = 2, max = 20, message = "Name should be between 2 and 20 characters")
    private String name;

    @NotBlank(message = "Enter surname")
    @Size(min = 2, max = 20, message = "Surname should be between 2 and 20 characters")
    private String surname;

    @NotBlank(message = "Specify gender")
    @ValueOfEnum(enumClass = Gender.class, message = "Allowed genders are MALE, FEMALE, UNDEFINED")
    private String gender;

    @NotBlank(message = "Enter birth date")
    @AgeXPlus(minAge = 18, message = "Age should be 18+")
    private String birthDate;

    @NotBlank(message = "Enter email")
    @Email(message = "Email should be valid")
    @Size(max = 50, message = "Email should be 50 characters or less")
    private String email;

    @NotBlank(message = "Enter phone number")
    @Size(max = 20, message = "Phone number should be less than 20 characters")
    @Pattern(regexp = "^[+]\\d+$", message = "Phone number should start with + and contain only numbers")
    private String phone;
}