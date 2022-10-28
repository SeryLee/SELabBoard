package com.example.selabboard.controller;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.service.BoardService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {
    @Setter(onMethod_ = @Autowired)
    private BoardService boardService;

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
        }
        return "Board/boardWrite";
    }

    @PostMapping("/insert")
    public String insertBoard(@ModelAttribute("board") WriteBoardForm writeBoardForm, HttpSession session) {
        Member member = (Member) session.getAttribute("loginMember");
        writeBoardForm.setDate(LocalDateTime.now());
        boardService.insertBoard(writeBoardForm, member);
        return "redirect:/board/list";
    }

    @PutMapping("/update")
    public String updateBoard(@ModelAttribute("board") Board board) {
        Long id = board.getId();
        String title = board.getTitle();
        String content = board.getContent();
        boardService.updateBoard(id, title, content);
        return "redirect:/board/list";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board/list";
    }

}

