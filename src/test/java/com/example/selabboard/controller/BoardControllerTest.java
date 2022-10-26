package com.example.selabboard.controller;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.repository.BoardRepository;
import com.example.selabboard.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardControllerTest {
    
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;


    @Test
    @DisplayName("보드 저장 테스트")
    void boardTest() {
        //초기 설정
        Member saveMember = memberRepository.save(createMember());
        Board createBoard = Board.createByWriteForm(createForm());

        // 비니지스로직
        createBoard.setMember(saveMember);
        boardRepository.save(createBoard);

        // 결과
        assertThat(saveMember.getBoards().size())
                .isEqualTo(1);
        assertThat(saveMember)
                .extracting("userId")
                .isEqualTo("ㅂㅈㄷ123");
        assertThat(saveMember.getBoards().get(0).getTitle())
                .isEqualTo("test");
        assertThat(saveMember.getBoards())
                .extracting("title").isNotEmpty();
    }


    @Test
    void 익명_글_테스트() {
        //초기 설정
        Board createBoard = Board.createByWriteForm(createForm());

        // 비니지스로직
        Board savedBoard = boardRepository.save(createBoard);

        // 결과
        Board findBoard = boardRepository.findOneByBoardId(createBoard.getId()).get();

        assertThat(savedBoard.getId()).isNotNull();
        assertThat(savedBoard).isEqualTo(findBoard);
        assertThat(findBoard.getMember()).isNull();
    }

    private Member createMember() {
        Member member = Member.builder()
                .name("이름")
                .address("주소")
                .password("비번")
                .userId("ㅂㅈㄷ123")
                .boards(new ArrayList<>())
                .build();
        return member;
    }

    private WriteBoardForm createForm() {
        WriteBoardForm writeBoardForm = new WriteBoardForm();
        writeBoardForm.setContent("1");
        writeBoardForm.setTitle("test");
        writeBoardForm.setDate(LocalDateTime.now());
        return writeBoardForm;
    }
}