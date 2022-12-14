package com.example.selabboard;

import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.model.entity.MemberRole;
import com.example.selabboard.repository.BoardRepository;
import com.example.selabboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class StartEventHandler {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void insertDefaultBoard() {
        Member defaultMember = createMember();
        memberRepository.save(defaultMember);
        List<Board> boards = new ArrayList<>();
        for (int i=0; i<150; i++) {
            Board board = createBoard(defaultMember);
            boards.add(board);
        }
        boardRepository.saveAll(boards);
    }

    private Board createBoard(Member defaultMember) {
        return Board.builder()
                .title("테스트 게시글")
                .content("서버 실행 후 자동 생성되는 게시글 입니다.")
                .date(LocalDateTime.now())
                .member(defaultMember)
                .build();
    }

    private Member createMember() {
        return Member.builder()
                .userId("admin@naver.com")
                .password(passwordEncoder.encode("1234"))
                .name("관리자")
                .address("관리자 주소")
                .boards(new ArrayList<>())
                .memberRole(MemberRole.ADMIN)
                .build();
    }
}
