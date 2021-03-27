package com.bemym8.serv;

import com.bemym8.models.User;
import com.bemym8.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * when a user has logged in successfully, a ShopmeUserDetails object is returned to Spring Security, and it will be set
 * as the principal object in the security context.
 * So when we retrieve the object that represents the currently logged-in user in the application, we actually get the
 * object returned by the loadUserByUsername() method, which wraps an object of the User entity class.
 */
public class UserDetailsWrapperService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with that name");
        }

        return new UserDetailsWrapper(user);
    }
}
