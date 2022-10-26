package com.example.selabboard.service;

import com.example.selabboard.model.dto.LoginMember;
import com.example.selabboard.model.entity.Member;

import java.util.Optional;

public interface MemberService {

    void addMember(Member newMember);

    boolean checkUserIdExist(String userId);

    Member loginMemberCheck(LoginMember loginMember);

}
