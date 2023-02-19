package com.example.clouddiplom.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDTO {
    @JsonProperty("auth-token")
    private String authToken;
}
