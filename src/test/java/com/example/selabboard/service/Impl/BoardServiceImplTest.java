package com.example.selabboard.service.Impl;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.service.BoardService;
import com.example.selabboard.service.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {

    @Autowired
    BoardService boardService;

    @Autowired
    MemberService memberService;

    @BeforeEach
    void init() {
        Member member = new Member();
        member.setName("테스트1");
        member.setAddress("테스트2");
        member.setUserId("테스트3");
        member.setPassword("테스트4");
        memberService.addMember(member);
    }



    @Test
    void insertTest() {
        WriteBoardForm board = new WriteBoardForm();
        board.setTitle("테스트");
        board.setContent("태스트");
        board.setDate(LocalDateTime.now());

        Member member = memberService.findById(1L);

        Board createBoard = Board.createByWriteForm(board);
        createBoard.setMember(member);

        assertThat(createBoard.getMember()).isEqualTo(member);

    }

}