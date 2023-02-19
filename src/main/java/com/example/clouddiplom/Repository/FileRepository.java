package com.example.clouddiplom.Repository;

import com.example.clouddiplom.Model.File;
import com.example.clouddiplom.Model.Person;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FileRepository extends JpaRepository<File, Integer> {

    //удалить по имени пользователя и названию файла
    void deleteByUserAndFilename(String fileName);

    // найти по пользователю и названию файла
    File findByUserAndFilename(String fileName);

    // редактировать название файла
    void editFileNameByUser(@Param("filename") String fileName, @Param("newName") String newName);

    // найти все файлы пользователя
    List<File> findAllByUser(Person person);
}
