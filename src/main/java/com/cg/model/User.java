package com.cg.model;

import com.cg.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;

    @OneToOne(targetEntity = Gender.class,fetch = FetchType.EAGER)
    private Gender gender;

    private String urlImage;

    private String phone;

    private String email;

    private String address;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(targetEntity = StatusAccount.class,fetch = FetchType.EAGER)
    private StatusAccount statusAccount;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullName + '\'' +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", role='" + role + '\'' +
                ", status='" + statusAccount + '\'' +
                '}';
    }

    public UserDTO toUserDTO() {
        return new UserDTO()
                .setId(id)
                .setFullName(fullName)
                .setUserName(userName)
                .setPassword(password)
                .setGender(gender)
                .setUrlImage(urlImage)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setRole(role.toRoleDTO())
                .setStatusAccount(statusAccount);
    }

}
