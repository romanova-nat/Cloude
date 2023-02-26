package com.example.clouddiplom.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "person")
public class Person {
        @Id
        @Column (name = "id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        @Column(nullable = false, name = "login")
        private String login;
        @Column(nullable = false, name = "password")
        private String password;
        @OneToMany(cascade = CascadeType.ALL) // // удалятся все файлы при удалении пользователя
        private List<File> files = new ArrayList<>();

        public Person(String login, String password, List<File> files) {
                this.login = login;
                this.password = password;
                this.files = files;
        }

        // добавляем пользователю файл в список
        public void addFileToPerson (File file) {
                file.setPerson(this);
                files.add(file);
        }

}
