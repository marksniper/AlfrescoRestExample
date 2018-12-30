package it.marco.semantic.config;

import it.marco.semantic.model.UserDetailsServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private CustomAuthenticationProvider authProvider;

    private final static Logger log = LoggerFactory.getLogger(RootConfig.class);

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImp();
    }

    @Override
    protected void configure(
            AuthenticationManagerBuilder auth) throws Exception
    {
        log.info("Configure userDetailsService e authenticationProvider");
        auth.userDetailsService(userDetailsService());
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        log.info("Configure site security");
        http.csrf().disable();
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .httpBasic().and().formLogin()
                .loginPage("/accedi")
                .permitAll(true)
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/accedi?logout")
                .permitAll()
                ;
    }

}
