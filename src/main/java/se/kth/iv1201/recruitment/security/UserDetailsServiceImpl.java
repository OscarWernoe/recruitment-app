package se.kth.iv1201.recruitment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.kth.iv1201.recruitment.domain.Person;
import se.kth.iv1201.recruitment.repository.PersonRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Autowired
    public UserDetailsServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(username));

        return UserDetailsImpl.create(person);
    }
}
