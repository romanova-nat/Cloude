package com.example.clouddiplom.Service;

import com.example.clouddiplom.Constant.Constant;
import com.example.clouddiplom.Exaption.UnauthorizedException;
import com.example.clouddiplom.Model.Person;
import com.example.clouddiplom.Repository.PersonRepository;
import com.example.clouddiplom.Security.PersonDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonDetailsService implements UserDetailsService {
    private PersonRepository personRepository;

    @Autowired
    public PersonDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username);

        if (person == null){
            throw new UnauthorizedException(Constant.USR_NOT_UNAUTHORIZED); //Пользоватль не авторизован
        //Оборачиваем в PersonDetails и возвращаем Person
    }
        return new PersonDetails(person);
    }
}

