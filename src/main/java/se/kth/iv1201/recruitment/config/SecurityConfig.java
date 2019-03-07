package se.kth.iv1201.recruitment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.kth.iv1201.recruitment.security.JwtAuthenticationEntryPoint;
import se.kth.iv1201.recruitment.security.JwtAuthenticationFilter;
import se.kth.iv1201.recruitment.security.UserDetailsServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Contains the security configuration for the application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * Creates an instance with the specified {@code UserDetailsService} and {@code JwtAuthenticationEntryPoint}.
     *
     * @param userDetailsService          The UserDetailsService instance
     * @param jwtAuthenticationEntryPoint The JwtAuthenticationEntryPoint instance
     */
    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    }

    /**
     * Creates a PasswordEncoder bean.
     *
     * @return An instance of PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        String idForEncode = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put(idForEncode, new BCryptPasswordEncoder());
        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }

    /**
     * Creates a JwtAuthenticationFilter bean.
     *
     * @return A JwtAuthenticationFilter instance
     * @throws Exception If error encountered
     */
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(), jwtAuthenticationEntryPoint);
    }

    /**
     * Creates an AuthenticationManager bean.
     *
     * @return An AuthenticationManager instance
     * @throws Exception If error encountered
     */
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configures the AuthenticationManager instance.
     *
     * @param auth The AuthenticationManager instance
     * @throws Exception If error encountered
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * Configures the security of the application.
     *
     * @param http The HttpSecurity instance
     * @throws Exception If error encountered
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests().antMatchers("/users", "/session").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(jwtAuthenticationFilter());
    }
}
