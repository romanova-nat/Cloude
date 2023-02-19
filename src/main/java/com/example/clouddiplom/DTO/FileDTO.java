package com.example.clouddiplom.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileDTO {
    @NotEmpty(message = "Не должно быть пустым")
    private String login;
    @NotEmpty(message = "Не должно быть пустым")
    private String password;
}
