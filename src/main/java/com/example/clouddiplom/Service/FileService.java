package com.example.clouddiplom.Service;

import com.example.clouddiplom.Constant.Constant;
import com.example.clouddiplom.Exaption.InputDataException;
import com.example.clouddiplom.Exaption.UnauthorizedException;
import com.example.clouddiplom.Model.File;
import com.example.clouddiplom.Model.Person;
import com.example.clouddiplom.Repository.AuthenticationRepository;
import com.example.clouddiplom.Repository.FileRepository;
import com.example.clouddiplom.Repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;


@Service
@Slf4j
public class FileService {
    @Autowired
    private FileRepository fileRepository;
    private PersonRepository personRepository;
    private AuthenticationRepository authenticationRepository;

    @Autowired
    public FileService(FileRepository fileRepository, PersonRepository personRepository,
                       AuthenticationRepository authenticationRepository) {
        this.fileRepository = fileRepository;
        this.personRepository = personRepository;
        this.authenticationRepository = authenticationRepository;
    }

    // Добавить файл
    public String uploadFile(String authToken, String filename, MultipartFile multipartFile) {
        Optional<Person> person = getUserByAuthToken(authToken);
        if (person == null) {
            throw new UnauthorizedException(Constant.USR_NOT_FOUND); // Пользователь не найден
        }
        try {
            fileRepository.save(new File(filename, LocalDateTime.now(), multipartFile.getSize(),
                            multipartFile.getBytes(), person));
            return Constant.SUCCESS_UPLOAD;  // Файл загружен
        } catch (IOException e) {
            throw new InputDataException(Constant.ERR_UPLOAD); // Ошибка загрузки файла
        }
    }

    private Optional<Person> getUserByAuthToken(String authToken) {
        if (authToken.startsWith("Bearer ")) {
            final String authTokenWithoutBearer = authToken.split(" ")[1];
            final String username = authenticationRepository.getUsernameByToken(authTokenWithoutBearer);
            return personRepository.findByUsername(username);
        }
        return null;
    }

    // Удалить
    @Transactional
    public void deleteFile(String authToken, String filename) {
        Optional<Person> person = getUserByAuthToken(authToken);
        if (person == null) {
            throw new UnauthorizedException(Constant.USR_NOT_FOUND); // Пользователь не найден
        }
        fileRepository.deleteByUserAndFilename(filename);
        System.out.println(Constant.SUCCESS_DEL); // Файл удален

        final File tryingToGetDeletedFile = fileRepository.findByUserAndFilename(filename);
        if (tryingToGetDeletedFile != null) {
            try {
                throw new InputDataException(Constant.ERR_DELETE); // Ошибка удаления файла
            } catch (InputDataException e) {
                throw new RuntimeException(e);
            }
        }
    }



//    public List<FileRS> getAllFiles(String authToken, Integer limit) {
//        final User user = getUserByAuthToken(authToken);
//        if (user == null) {
//            log.error("Get all files: Unauthorized");
//            throw new UnauthorizedException("Get all files: Unauthorized");
//        }
//        log.info("Success get all files. User {}", user.getUsername());
//        return storageFileRepository.findAllByUser(user).stream()
//                .map(o -> new FileRS(o.getFilename(), o.getSize()))
//                .collect(Collectors.toList());
//    }



//
//    //Скачать
//    public byte[] downloadFile(String filename) {
//        final File file = fileRepository.findByUserAndFilename(filename);
//        return file.getContent();
//    }
//
//    // Переименовать
//    public void editFileName(String filename, String newName) {
//        fileRepository.editFileNameByUser(filename, newName);
//    }
//
//    public Optional<File> getFile(String id) {
//        return fileRepository.findById(id);
//    }
//
//    public List<File> getAllFiles(Person person) {
//        return fileRepository.findAllByUser(person);
//    }
}
