package com.example.clouddiplom.Repository;

import com.example.clouddiplom.Model.File;
import com.example.clouddiplom.Model.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<File, Integer> {

    //удалить по имени пользователя и названию файла
    void deleteByUserAndFilename(Optional<Person> person, String fileName);

    // найти по пользователю и названию файла
    File findByUserAndFilename(Optional<Person> person, String fileName);

    // редактировать название файла
    void editFileNameByUser(@Param("person") Person person, @Param("filename") String fileName, @Param("newName") String newName);

    // найти все файлы пользователя
    List<File> findAllByUser(Person person);
}
