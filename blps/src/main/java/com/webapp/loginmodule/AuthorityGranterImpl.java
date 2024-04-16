package com.webapp.loginmodule;

import com.webapp.exceptioin.ResourceNotFoundException;
import com.webapp.model.UserEntity;
import com.webapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.jaas.AuthorityGranter;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
public class AuthorityGranterImpl implements AuthorityGranter {

    private final UserRepository userRepository;
    @Override
    public Set<String> grant(Principal principal) {
        Optional<UserEntity> user = userRepository.findUserByEmail(principal.getName());
        if (user.isPresent()) {
            return Collections.singleton(user.get().getRole().name());
        }
        throw new ResourceNotFoundException("USER_CREDITIONS_ERROR");
    }
}
