package com.example.selabboard.model.entity;

import com.example.selabboard.model.dto.WriteBoardForm;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "member")
@Builder
@Entity(name = "board")
public class Board {

    @Id @Column(name = "Id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberid")
    private Member member;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "date")
    private LocalDateTime date;

    @Column
    private Long fileId;

    public void setMember(Member member) {
        this.member = member;
        member.getBoards().add(this);
    }

    public static Board createByWriteForm(WriteBoardForm writeBoardForm) {
        Board board = new Board();
        board.setTitle(writeBoardForm.getTitle());
        board.setContent(writeBoardForm.getContent());
        board.setDate(writeBoardForm.getDate());
        board.setFileId(writeBoardForm.getFileId());
        board.setMember(new Member());
        return board;
    }

}
