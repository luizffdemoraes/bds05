package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public UserDTO findByUser() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return new UserDTO(repository.findByEmail(username));
        } catch (Exception e) {
            throw new UnauthorizedException("Invalid user");
        }
    }
}
