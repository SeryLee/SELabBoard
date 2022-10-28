package com.example.selabboard.service.Impl;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.repository.BoardRepository;
import com.example.selabboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<Board> selectBoardList() {
        return boardRepository.findAllBy();
    }

    @Override
    public Board selectBoardDetail(Long id) {
        return boardRepository.findOneByBoardId(id)
                .orElseThrow( () -> new NoSuchElementException("게시판이 존재하지 않습니다. board id = " + id));
    }

    @Override
    public void insertBoard(WriteBoardForm board, Member member) {
        Board createBoard = Board.createByWriteForm(board);
        createBoard.setMember(member);
        boardRepository.save(createBoard);
    }

    @Override
    public void updateBoard(Long id, String title, String content) {
        boardRepository.update(id, title, content);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }


}