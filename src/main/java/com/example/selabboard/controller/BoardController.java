package com.example.selabboard.controller;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String getBoardList(Model model) throws Exception {
        List<Board> boards = boardService.selectBoardList();
        model.addAttribute("boardList", boards);
        return "Board/board";
    }

    @GetMapping("/detail")
    public String getBoardDetail(@RequestParam(value = "id", required = false) Long boardId, Model model) {
        if(boardId == null) {
            return "redirect:/board/list";
        }
        Board selectedBoard = boardService.selectBoardDetail(boardId);
        model.addAttribute("board", selectedBoard);
        return "Board/boardDetail";
    }

    @GetMapping("/write")
    public String writeBoard(@ModelAttribute("board") Board board, Model model){
        if(board.getId() != null) {
            model.addAttribute("boardInfo", board);
            return "Board/boardUpdate";
        }
        return "Board/boardWrite";
    }

    @PostMapping("/insert")
    public String insertBoard(@ModelAttribute("board") WriteBoardForm writeBoardForm, HttpSession session) {
        Long loginMemberId = (Long) session.getAttribute("loginMemberId");

        boardService.insertBoard(writeBoardForm, loginMemberId);
        return "redirect:/board/list";
    }

    @PutMapping("/update")
    public String updateBoard(@ModelAttribute("board") Board board) {
        boardService.updateBoard(board);
        return "redirect:/board/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

}

