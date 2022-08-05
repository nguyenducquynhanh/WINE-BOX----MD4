package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.dto.ProductDTO;
import com.cg.model.dto.UserDTO;
import com.cg.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {
    User getByUserName(String userName);

    Optional<User> findByUserName(String userName);

    Optional<UserDTO> findUserDTOByUserName(String userName);

    List<UserDTO> findAllUserDTO();

    Optional<UserDTO> findUserDTOByID(Long id);

    void blockAccount(Long id);

    void unblockAccount(Long id);
}
