package it.marco.semantic.config;

import it.marco.semantic.Alfresco.LoginAlfresco;
import it.marco.semantic.model.Utente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider
{
    @Autowired
    LoginAlfresco loginAlfresco;

    private final static Logger log = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException
    {
        log.info("Controllo autenticazioni");
        Utente.getInstance().setPassword(authentication.getCredentials().toString());
        Utente.getInstance().setUsername(authentication.getName());
        Utente.getInstance().setRole("ROLE_USER");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        String token = loginAlfresco.loginPost();
        if (!(token.isEmpty()))
            {
                log.info("User is authenticated");
                Utente.getInstance().setAlfrescoTicket(token);
                return new UsernamePasswordAuthenticationToken
                        (Utente.getInstance().getUsername(), Utente.getInstance().getPassword(), grantedAuthorities);
            }
        else
            {
                log.warn("Warning! User did not pass authentication with Alfresco");
                Utente.setInstance(null);
                throw new BadCredentialsException("Error in log in");
            }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

}