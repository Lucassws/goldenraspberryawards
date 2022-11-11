package com.goldenraspberryawards.domain;

import com.goldenraspberryawards.domain.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {

    List<Filme> findByWinner(Boolean vencedor);

}
