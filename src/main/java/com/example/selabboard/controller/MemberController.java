package com.example.selabboard.controller;

import com.example.selabboard.model.dto.JoinMember;
import com.example.selabboard.model.dto.LoginMember;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/join")
    public String joinUserForm(@ModelAttribute("member") JoinMember joinMember) {
        return "Member/join";
    }

    @PostMapping("/join_check")
    public String joinUser(@Valid @ModelAttribute("member") JoinMember joinMember, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "Member/join";
        }
        String userId = joinMember.getUserId();
        boolean duplicateIdCheck = memberService.checkUserIdExist(userId);

        if(duplicateIdCheck) {
            bindingResult.reject("duplicateId", "이미 존재하는 아이디입니다");
            return "Member/join";
        }

        Member member = Member.createByJoinMember(joinMember);

        memberService.addMember(member);

        model.addAttribute("message", "회원가입이 완료되었습니다.");
        model.addAttribute("href", "/member/login");

        return "alert";
    }

    @GetMapping("/login")
    public String loginUserForm(@ModelAttribute("member") LoginMember loginMember, @RequestParam(value = "redirectURI", required = false) String redirectURI, Model model) {
        model.addAttribute("redirectURI", redirectURI);
        return "Member/login";
    }

    @PostMapping("/login_check")
    public String loginUser(@Valid @ModelAttribute("member") LoginMember loginMember, BindingResult bindingResult, HttpServletRequest request, @RequestParam(value = "redirectURI", required = false) String redirectURI) {
        if(bindingResult.hasErrors()) {
            return "Member/login";
        }

        Member memberCheck = memberService.loginMemberCheck(loginMember);

        if(memberCheck == null) {
            bindingResult.reject("loginFail", "아이디와 비밀번호를 다시 확인하세요");
            return "Member/login";
        }

        /*cookie-login*/
        /*Cookie cookie = new Cookie("userId", String.valueOf(memberCheck.getUserId()));
        cookie.setPath("/");
        response.addCookie(cookie);*/

        //session-login
        HttpSession session = request.getSession();
        session.setAttribute("loginMemberId", memberCheck.getId());

        if (redirectURI.equals("") || redirectURI == null) {
            return "redirect:/";
        }

        return "redirect:" + redirectURI;

    }

    /*cookie - logout*/
    /*@GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/";
    }*/

    /*session - logout*/
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}