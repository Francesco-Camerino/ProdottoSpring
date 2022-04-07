package com.example.ProdottoSpring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Prodotto {
    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private Date dataAcquisto;
    private Date dataCreazione;
    private Date dataScadenza;
    private float ranking;
    private float prezzo;
    private float quantita;

    public Prodotto() {
    }

    public Prodotto(String nome, float prezzo) {
        this.nome = nome;
        this.prezzo = prezzo;
    }

    public Prodotto(String nome, Date dataAcquisto, Date dataCreazione, Date dataScadenza, float ranking, float prezzo, float quantita) {
        this.nome = nome;
        this.dataAcquisto = dataAcquisto;
        this.dataCreazione = dataCreazione;
        this.dataScadenza = dataScadenza;
        this.ranking = ranking;
        this.prezzo = prezzo;
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "Nome: "+this.getNome()+" Data di acquisto: "+this.getDataAcquisto()+"\n"+
                "Data di creazione DB: "+this.getDataCreazione()+" Data scadenza: "+this.getDataScadenza()+"\n"+
                "Ranking: "+this.getRanking()+" Prezzo: "+this.getPrezzo()+" Quantità: "+this.getQuantita()+"\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataAcquisto() {
        return dataAcquisto;
    }

    public void setDataAcquisto(Date dataAcquisto) {
        this.dataAcquisto = dataAcquisto;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Date getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(Date dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public float getRanking() {
        return ranking;
    }

    public void setRanking(float ranking) {
        this.ranking = ranking;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public float getQuantita() {
        return quantita;
    }

    public void setQuantita(float quantita) {
        this.quantita = quantita;
    }
}
