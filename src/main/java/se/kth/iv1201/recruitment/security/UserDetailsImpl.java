package se.kth.iv1201.recruitment.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.kth.iv1201.recruitment.domain.Person;

import java.util.Collection;

public class UserDetailsImpl implements UserDetails {
    private String username;
    private String password;
    private String role;

    public UserDetailsImpl(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static UserDetailsImpl create(Person person) {
        return new UserDetailsImpl(person.getUsername(), person.getPassword(), person.getRole().getName());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return username;
    }

    @Override
    public String getUsername() {
        return password;
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
