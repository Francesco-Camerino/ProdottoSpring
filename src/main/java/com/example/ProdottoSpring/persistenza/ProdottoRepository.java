package com.example.ProdottoSpring.persistenza;

import com.example.ProdottoSpring.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {
    List<Prodotto> findByNome(String nome);
    List<Prodotto> findByDataAcquistoBetween(Date datada, Date dataa);
    List<Prodotto> findByDataCreazioneBetween(Date datada, Date dataa);
    List<Prodotto> findByDataScadenzaBetween(Date datada, Date dataa);
    List<Prodotto> findByRankingBetween(float min, float max);
    List<Prodotto> findByRankingLessThan(float max);
}
