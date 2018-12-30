package it.marco.semantic.model;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImp implements UserDetailsService
{
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User.UserBuilder builder;
        if (Utente.getInstance().getUsername() != null)
        {

            builder = User.withUsername(Utente.getInstance().getUsername());
            builder.password(Utente.getInstance().getPassword());
            builder.roles(Utente.getInstance().getPassword());
        }
        else
        {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();

    }


}