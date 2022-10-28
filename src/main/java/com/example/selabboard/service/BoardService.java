package com.example.selabboard.service;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;

import java.util.List;

public interface BoardService {
    List<Board> selectBoardList() throws Exception;
    Board selectBoardDetail(Long boardId);
    void insertBoard(WriteBoardForm board, Member member);
    void updateBoard(Long id, String title, String content);
    void deleteBoard(Long id);

}