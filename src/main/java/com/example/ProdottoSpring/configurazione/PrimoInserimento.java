package com.example.ProdottoSpring.configurazione;

import com.example.ProdottoSpring.model.Prodotto;
import com.example.ProdottoSpring.persistenza.ProdottoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class PrimoInserimento {
    private static final Logger logger = LoggerFactory.getLogger(PrimoInserimento.class);
    @Bean
    CommandLineRunner inserisciElementi(ProdottoRepository repository) {
        return args -> {
            List<Prodotto> listaProdotti = new ArrayList<>();
            listaProdotti.add(new Prodotto("pentola", 25.45f));
            listaProdotti.add(new Prodotto("palla", 10.30f));
            listaProdotti.add(new Prodotto("cucchiaio", 2.50f));

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date dataCreazione = null;
            Date dataScadenza = null;
            try {
                dataCreazione = dateFormat.parse("25-11-2021");
                dataScadenza = dateFormat.parse("13-04-2023");
            } catch (ParseException e) {
                logger.error("Formato data non supportata!", e);
            }
            if (dataCreazione != null) {
                listaProdotti.add(new Prodotto("Gocciole", new Date(), dataCreazione, dataScadenza, 4.2f, 3.25f, 30));
            }

            for (Prodotto p : listaProdotti) {
                logger.info(p.toString());
            }
            repository.saveAll(listaProdotti);
        };
    }
}
