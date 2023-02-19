package com.example.clouddiplom.Service;

import com.example.clouddiplom.Model.Person;
import com.example.clouddiplom.Security.PersonDetails;
import com.example.clouddiplom.Repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PersonDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);

        if (person.isEmpty())
            throw new UsernameNotFoundException("User not found");
        //Оборачиваем в PersonDetails и возвращаем Person
        return new PersonDetails(person.get());
    }
}