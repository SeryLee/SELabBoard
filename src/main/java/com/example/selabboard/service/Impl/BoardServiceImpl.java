package com.example.selabboard.service.Impl;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.repository.BoardRepository;
import com.example.selabboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public Page<Board> selectBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    public Board selectBoardDetail(Long id) {
        return boardRepository.findOneByBoardId(id)
                .orElseThrow( () -> new NoSuchElementException("게시판이 존재하지 않습니다. board id = " + id));
    }

    @Override
    public void insertBoard(WriteBoardForm board, Member loginMember) {

        Board createBoard = Board.createByWriteForm(board);

        createBoard.setMember(loginMember);
        createBoard.setDate(LocalDateTime.now());
        boardRepository.save(createBoard);
    }

    @Override
    public void updateBoard(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }


}
