package com.example.clouddiplom.Repository;

import com.example.clouddiplom.Model.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface PersonRepository extends JpaRepository<Person, Integer> {

    //Ищем пользователя, который пришел по имени и сверяем его с БД
    Optional<Person> findByUsername(String username);

}
