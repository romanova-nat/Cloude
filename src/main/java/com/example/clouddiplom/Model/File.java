package com.example.clouddiplom.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(appliesTo = "File")
public class File {
    @jakarta.persistence.Id
    @Id
    @Column(name = "id")
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
    public Person person;


    public File(String filename, LocalDateTime localDateTime, long size, byte[] bytes, Optional<Person> person) {
    }
}
