package com.doslab.human_detector;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = DataBase.get_user(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Collection<GrantedAuthority> authorities = getAuthorities(user.get_role_id());

        return new org.springframework.security.core.userdetails.User(
                user.get_fullname(),
                user.get_password_hash(),
                authorities // Передаем роли
        );
    }

    private Collection<GrantedAuthority> getAuthorities(int role_id) {
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        Role role = DataBase.get_role(role_id); // Получаем роль из базы данных

        if (role != null) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.get_name()));
        }

        return authorities;
    }

}