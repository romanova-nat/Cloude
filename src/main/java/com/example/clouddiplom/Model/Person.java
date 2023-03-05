package com.example.clouddiplom.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Person {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;
        @Column(nullable = false)
        private String login;
        @Column(nullable = false)
        private String password;
        @OneToMany(cascade = CascadeType.ALL) // // удалятся все файлы при удалении пользователя
        private List<File> listOfFiles = new ArrayList<>();

        // добавляем пользователю файл в список
        public void addFileToPerson (File file) {
                file.setPerson(this);
                listOfFiles.add(file);
        }

}
