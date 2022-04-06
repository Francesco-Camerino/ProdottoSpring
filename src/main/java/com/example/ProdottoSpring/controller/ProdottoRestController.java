package com.example.ProdottoSpring.controller;

import com.example.ProdottoSpring.avviso.ProdottoNonTrovato;
import com.example.ProdottoSpring.model.Prodotto;
import com.example.ProdottoSpring.persistenza.ProdottoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Date;
import java.util.List;

@RestController
public class ProdottoRestController {
    private static final Logger logger = LoggerFactory.getLogger(ProdottoRestController.class);
    private ProdottoRepository repository;

    ProdottoRestController(ProdottoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/prodotti")
    public List<Prodotto> leggiTuttiIProdotti() {
        logger.info("Prendo tutti i prodotti");
        return repository.findAll();
    }
    @GetMapping("/prodotti/ricerca/nome/{nome}")
    List<Prodotto> cercaPerNome(@PathVariable String nome) {
        return repository.findByNome(nome);
    }

    @GetMapping("/prodotto/{id}")
    public Prodotto trovaProdottoConID(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdottoNonTrovato(id));
    }

    @GetMapping("/prodotto/ricercatradateacquisto")
    public List<Prodotto> ricercaProdottoTraDateAcquisto(
            @RequestParam(name = "datada") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ) {
        return repository.findByDataAcquistoBetween(datada, dataa);
    }

    @GetMapping("/prodotto/ricercatradatecreazione")
    public List<Prodotto> ricercaProdottoConDateCreazione(
            @RequestParam(name = "datada") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ) {
        return repository.findByDataCreazioneBetween(datada, dataa);
    }

    @GetMapping("/prodotto/ricercatradatescadenza")
    public List<Prodotto> ricercaProdottoConDateScadenza(
            @RequestParam(name = "datada") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date datada,
            @RequestParam(name = "dataa") @DateTimeFormat(pattern = "dd-MM-yyyy")
                    Date dataa
    ) {
        return repository.findByDataScadenzaBetween(datada, dataa);
    }

    @GetMapping("/prodotto/ranking")
    public List<Prodotto> ricercaProdottoConRanking(
            @RequestParam(name = "min") float min,
            @RequestParam(name = "max") float max
    ) {
        return repository.findByRankingBetween(min, max);
    }

    @GetMapping("/prodotto/rankingmin")
    public List<Prodotto> ricercaProdottoConRankingMin(
            @RequestParam(name = "max") float max
    ) {
        return repository.findByRankingLessThan(max);
    }
    /* Caricamento di file */
    @PostMapping("/caricafile")
    public  String caricaFile(@RequestParam ("file") MultipartFile file){
        String infoFile= file.getOriginalFilename() + " - "+file.getContentType();
        String conFormat = String.format("%S-%S", file.getOriginalFilename(),file.getContentType());
        logger.info((infoFile));
        logger.warn(conFormat);
        return conFormat;
    }

    @PostMapping("/prodotto")
    public Prodotto inserisciUnNuovoProdotto(@RequestBody Prodotto nuovoProdotto) {
        return repository.save(nuovoProdotto);
    }

    @PutMapping("/prodotto/{id}")
    public Prodotto aggiornaDatiProdotto(@PathVariable Long id,
                                     @RequestBody Prodotto prodotto) {
        return repository.findById(id).map(
                /* crea un nuovo prodotto */
                nuovoProdotto -> {
                    nuovoProdotto.setNome(prodotto.getNome());
                    nuovoProdotto.setDataAcquisto(prodotto.getDataAcquisto());
                    nuovoProdotto.setPrezzo(prodotto.getPrezzo());
                    return repository.save(nuovoProdotto);
                }
        ).orElseGet(
                () -> {
                    prodotto.setId(id);
                    return repository.save(prodotto);
                }

        );
    }

    @PutMapping("/prodottoput")
    public Prodotto aggiornaSingoloProdotto(@RequestBody Prodotto prodotto) {
        return repository.save(prodotto);
    }

    @DeleteMapping("/prodotto/{id}")
    void eliminaProdotto(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
