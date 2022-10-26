package com.example.selabboard.repository;

import com.example.selabboard.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select count(m.userId) from member m where m.userId = :userId")
    Long checkUserIdExist(String userId);

    @Query("select m from member m where m.userId = :userId")
    Optional<Member> findByUserId(String userId);

}
