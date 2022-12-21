package com.example.selabboard.repository;

import com.example.selabboard.model.entity.Board;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from board b")
    List<Board> findAllBy();

    @Query("select b from board b where b.id = :id")
    Optional<Board> findOneByBoardId(long id);

}
