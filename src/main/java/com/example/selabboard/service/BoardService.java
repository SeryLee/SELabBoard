package com.example.selabboard.service;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<Board> selectBoardList(Pageable pageable) throws Exception;
    Board selectBoardDetail(Long boardId);
    void insertBoard(WriteBoardForm board, Member loginMember);
    void deleteBoard(Long id);
    void updateBoard(Board board);
}
