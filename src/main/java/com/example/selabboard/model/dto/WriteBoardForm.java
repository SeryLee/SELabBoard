package com.example.selabboard.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class WriteBoardForm {
    @NotBlank
    private String title;
    @NotEmpty
    private String content;
    private LocalDateTime date;
}
