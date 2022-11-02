package com.example.selabboard.model.entity;

import com.example.selabboard.model.dto.JoinMember;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "member")
public class Member {

    @Id @GeneratedValue
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<Board> boards = new ArrayList<>();

    @Column(name = "userid")
    private String userId;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    public void addBoard(Board board) {
        board.setMember(this);
        this.boards.add(board);
    }

    public static Member createByJoinMember(JoinMember joinMember) {
        Member member = new Member();
        member.setUserId(joinMember.getUserId());
        member.setPassword(joinMember.getPassword());
        member.setName(joinMember.getName());
        member.setAddress(joinMember.getAddress());
        member.setBoards(new ArrayList<>());
        return member;
    }
}
