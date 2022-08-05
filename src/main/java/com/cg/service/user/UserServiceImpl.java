package com.cg.service.user;

import com.cg.model.User;
import com.cg.model.UserPrinciple;
import com.cg.model.dto.UserDTO;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByUserName(String userName) {
        return userRepository.getByUserName(userName);
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Optional<UserDTO> findUserDTOByUserName(String userName) {
        return userRepository.findUserDTOByUserName(userName);
    }

    @Override
    public List<UserDTO> findAllUserDTO() {
        return userRepository.findAllUserDTO();
    }

    @Override
    public Optional<UserDTO> findUserDTOByID(Long id) {
        return userRepository.findUserDTOByID(id);
    }

    @Override
    public void blockAccount(Long id) {
         userRepository.blockAccount(id);
    }

    @Override
    public void unblockAccount(Long id) {
        userRepository.unlockAccount(id);
    }

    @Override
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (!userOptional.isPresent()) {
            throw new UsernameNotFoundException(userName);
        }
        return UserPrinciple.build(userOptional.get());
    }

}
