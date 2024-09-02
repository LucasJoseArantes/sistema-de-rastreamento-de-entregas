package br.edu.iftm.rastreamento.exception;

public class PacoteNotFoundException extends RuntimeException {
    public PacoteNotFoundException(Long id) {
        super("Pacote com id " + id + " não foi encontrado.");
    }
}
