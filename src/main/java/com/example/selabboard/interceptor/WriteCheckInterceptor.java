package com.example.selabboard.interceptor;

import com.example.selabboard.model.entity.Board;
import com.example.selabboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
public class WriteCheckInterceptor implements HandlerInterceptor {

    private final BoardService boardService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(request.getParameter("id") == null) {
            return true;
        }
        Long boardId = Long.valueOf(request.getParameter("id"));
        Board board = boardService.selectBoardDetail(boardId);

        HttpSession session = request.getSession();
        Long id = (Long) session.getAttribute("loginMemberId");

        if(!board.getMember().getId().equals(id)) {
            response.sendRedirect("/board/list");
            return false;
        }
        return true;
    }
}
