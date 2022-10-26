package com.example.selabboard.model.dto;

import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WriteBoardForm {
    private String title;
    private String content;
    private LocalDateTime date;


}
