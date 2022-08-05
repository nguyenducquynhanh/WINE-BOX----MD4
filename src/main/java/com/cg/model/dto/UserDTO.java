package com.cg.model.dto;

import com.cg.model.Gender;
import com.cg.model.Role;
import com.cg.model.StatusAccount;
import com.cg.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    @NotBlank(message = "The full name is required!")
    @Size(min = 1,max = 100, message = "The length of full name must be between 1 and 100 characters!")
    private String fullName;

    @NotBlank(message = "The user name is required!")
    @Size(min = 5, max = 50, message = "The length of user name must be between 5 and 50 characters!")
    private String userName;

//    @NotBlank(message = "The password is required!")
//    @Size(min = 3, max = 30, message = "The length of password must be between 3 and 30 characters!")
    private String password;

    private Gender gender;

    @NotBlank(message = "Url image is not blank!")
    @Size(min = 10, max = 10000, message = "Length of url image in between 10 to 10.000")
    private String urlImage;

    @NotBlank(message = "The phone number is required!")
    @Size(min = 9, max = 15, message = "The phone is invalid!")
    private String phone;

    @NotBlank(message = "The email is required!")
    @Email(message = "The email address is invalid!")
    @Size(min= 5, max = 100, message = "The length of email must be between 5 and 100 characters!")
    private String email;

    @NotBlank(message = "The address is required!")
    @Size(min= 2, max = 100, message = "The length of address must be between 2 and 100 characters!")
    private String address;

    @Valid
    private RoleDTO role;

    private StatusAccount statusAccount;

    public UserDTO(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setFullName(fullName)
                .setUserName(userName)
                .setPassword(password)
                .setGender(gender)
                .setUrlImage(urlImage)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setRole(role.toRole())
                .setStatusAccount(statusAccount);
    }

    public UserDTO(Long id, String fullName, String userName, String password, Gender gender, String urlImage, String phone, String email, String address, Role role, StatusAccount statusAccount) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.urlImage = urlImage;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.role = role.toRoleDTO();
        this.statusAccount = statusAccount;
    }
}
