package com.example.selabboard.service.Impl;

import com.example.selabboard.model.dto.LoginMember;
import com.example.selabboard.model.entity.Member;
import com.example.selabboard.repository.MemberRepository;
import com.example.selabboard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void addMember(Member newMember) {
        String encodedPassword = passwordEncoder.encode(newMember.getPassword());
        newMember.setPassword(encodedPassword);
        memberRepository.save(newMember);
    }

    @Override
    public boolean checkUserIdExist(String userId) {
        Long cnt = memberRepository.checkUserIdExist(userId);

        if(cnt > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Member loginMemberCheck(LoginMember loginMember) {
        String userId = loginMember.getUserId();
        String password = loginMember.getPassword();
        return memberRepository.findByUserId(userId)
                .filter(m -> passwordEncoder.matches(password, m.getPassword()))
                .orElse(null);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(NullPointerException::new);
    }

}
