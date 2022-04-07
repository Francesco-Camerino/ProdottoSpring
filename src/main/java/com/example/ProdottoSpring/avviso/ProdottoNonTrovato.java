package com.example.ProdottoSpring.avviso;

public class ProdottoNonTrovato extends RuntimeException{
    public ProdottoNonTrovato(Long id) {
        super("Prodotto con attributo: "+id+" non trovato");
    }
}
