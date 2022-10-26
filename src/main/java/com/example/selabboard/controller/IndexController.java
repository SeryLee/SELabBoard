package com.example.selabboard.controller;

import com.example.selabboard.model.entity.Member;
import com.example.selabboard.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {

    private MemberRepository memberRepository;

    public IndexController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/")
    public String index(Model model
//          , @CookieValue(name = "userId", required = false) String userId
            , HttpSession session) {

        //cookie
        //if(userId == null) {return "index";}
        //Member member = memberRepository.findByLoginUserId(userId);

        //session
        Member member = (Member) session.getAttribute("loginMember");
        if(member == null) {return "index";}
        model.addAttribute("member", member);
        return "index";
    }
}
