package com.cg.repository;

import com.cg.model.User;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUserName(String userName);

    Optional<User> findByUserName(String userName);

    @Query("SELECT NEW com.cg.model.dto.UserDTO (u.id, u.userName) FROM User u WHERE u.userName = ?1")
    Optional<UserDTO> findUserDTOByUserName(String userName);

    @Query ("SELECT NEW com.cg.model.dto.UserDTO (" +
                "u.id, " +
                "u.fullName," +
                "u.userName," +
                "u.password, " +
                "u.gender, " +
                "u.urlImage, " +
                "u.phone, " +
                "u.email, " +
                "u.address, " +
                "u.role, " +
                "u.statusAccount " +
            ") " +
            "FROM User AS u " +
            "ORDER BY u.id"
    )
    List<UserDTO> findAllUserDTO();

    @Query ("SELECT NEW com.cg.model.dto.UserDTO (" +
            "u.id, " +
            "u.fullName," +
            "u.userName," +
            "u.password, " +
            "u.gender, " +
            "u.urlImage, " +
            "u.phone, " +
            "u.email, " +
            "u.address, " +
            "u.role, " +
            "u.statusAccount " +
            ") " +
            "FROM User AS u " +
            "WHERE u.id = :id"
    )
    Optional<UserDTO> findUserDTOByID(@Param("id") long id);


    @Modifying
    @Query("UPDATE User AS u SET u.statusAccount.id = 2 WHERE u.id = :id")
    void blockAccount(@Param("id") Long id);

    @Modifying
    @Query("UPDATE User AS u SET u.statusAccount.id = 1 WHERE u.id = :id")
    void unlockAccount(@Param("id") Long id);

}


