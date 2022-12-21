package com.example.selabboard.service;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> selectBoardList() throws Exception;
    Board selectBoardDetail(Long boardId);
    void insertBoard(WriteBoardForm board, Long memberId);
    void deleteBoard(Long id);
    void updateBoard(Board board);
}
