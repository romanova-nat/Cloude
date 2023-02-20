package com.example.clouddiplom.Controller;

import com.example.clouddiplom.Constant.Constant;
import com.example.clouddiplom.Service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
public class FileController {

    @Autowired
    private FileService fileService;

//    //Добавить
    @PostMapping("/file")
    public String uploadFile(@RequestHeader("auth-token") String authToken,
                                        @RequestParam("filename") String filename, MultipartFile file) {
        fileService.uploadFile(authToken, filename, file);
        return Constant.SUCCESS_UPLOAD;  // Файл загружен
    }

    //Удалить
    @DeleteMapping("/file")
    public String deleteFile(@RequestHeader("auth-token") String authToken,
                             @RequestParam("filename") String filename) {
        fileService.deleteFile(authToken, filename);
        return Constant.SUCCESS_DEL; // Файл удален
    }

    //Скачать
//    @GetMapping("/file")
//    public ResponseEntity<Resource> downloadFile(@RequestParam("fileName") String fileName) {
//        byte[] file = cloudService.downloadFile(fileName);
//        return ResponseEntity.ok().body((Resource) new ByteArrayResource(file));
//    }


//    // Поменять имя файла
//    @PutMapping(value = "/file")
//    public ResponseEntity<?> editFileName(@RequestParam("fileName") String fileName,
//                                          @RequestBody String newName) {
//        cloudService.editFileName(fileName, newName);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
//
//    //Показать все файлы пользователя
//    @GetMapping("/list")
//    public List<File> getAllFiles(Person person) {
//        return cloudService.getAllFiles(person);
//    }
}
