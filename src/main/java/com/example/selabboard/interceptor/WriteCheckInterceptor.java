package com.example.selabboard.interceptor;

import com.example.selabboard.model.entity.Board;
import com.example.selabboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WriteCheckInterceptor implements HandlerInterceptor {

    @Autowired
    private BoardService boardService;

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
        System.out.println("writer check success");
        return true;
    }
}
