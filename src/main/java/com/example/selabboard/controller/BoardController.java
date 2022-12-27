package com.example.selabboard.controller;

import com.example.selabboard.model.dto.WriteBoardForm;
import com.example.selabboard.model.entity.Board;
import com.example.selabboard.model.entity.File;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.repository.FileRepository;
import com.example.selabboard.repository.MemberRepository;
import com.example.selabboard.service.BoardService;
import com.example.selabboard.service.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.UriUtil;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final MemberRepository memberRepository;
    private final BoardService boardService;
    private final FileService fileService;

    @GetMapping("/list")
    public String getBoardList(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<Board> boards = boardService.selectBoardList(pageable);
        model.addAttribute("boardList", boards);
        model.addAttribute("maxPage", 5);
        return "Board/board";
    }

    @GetMapping("/detail")
    public String getBoardDetail(@RequestParam(value = "id", required = false) Long boardId, Model model) {
        if(boardId == null) {
            return "redirect:/board/list";
        }
        Board selectedBoard = boardService.selectBoardDetail(boardId);

        Long fileId = selectedBoard.getFileId();
        File findFile = fileService.getFile(fileId);

        model.addAttribute("board", selectedBoard);
        model.addAttribute("file", findFile);
        return "Board/boardDetail";
    }

    @GetMapping("/downloadFile/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("fileId") Long id) throws MalformedURLException {
        File file = fileService.getFile(id);
        UrlResource resource = new UrlResource("file:" + file.getSavedPath());
        String encodedFileName = UriUtils.encode(file.getOriginalName(), StandardCharsets.UTF_8);

        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(resource);
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
    public String insertBoard(@Valid @ModelAttribute("board") WriteBoardForm writeBoardForm, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
        Long loginMemberId = (Long) session.getAttribute("loginMemberId");
        Member loginMember = memberRepository.findById(loginMemberId)
                .orElse(null);

        Long fileId = fileService.saveFile(file);
        writeBoardForm.setFileId(fileId);

        boardService.insertBoard(writeBoardForm, loginMember);
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

