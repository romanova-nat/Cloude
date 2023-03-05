package com.example.clouddiplom.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "fileName")
    private String fileName;

    @Column(name = "size")
    private Long size;

    @Column (name = "data")
    private LocalDateTime data;

    @Lob
    private byte[] content;

    @ManyToOne(cascade = CascadeType.REFRESH) // обновиться при удалении, foreign key
    private Person person;

    public File(String fileName, Long size, LocalDateTime data, byte[] content, Optional<Person> person) {
        this.fileName = fileName;
        this.size = size;
        this.data = data;
        this.content = content;
    }

}
