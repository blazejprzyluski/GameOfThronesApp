package com.gameofthronesapp.services;

import com.gameofthronesapp.databaseutils.CustomUserDao;
import com.gameofthronesapp.datamodel.CustomUser;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserSecurityService implements UserDetailsService {
    private final CustomUserDao dao = new CustomUserDao();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUser customUser = dao.findUserByEmail(username);

        return User.withUsername(customUser.getEmail())
                .password(customUser.getPassword())
                .authorities("USER").build();
    }
}
