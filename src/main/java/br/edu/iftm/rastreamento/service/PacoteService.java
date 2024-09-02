package br.edu.iftm.rastreamento.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.model.Rastreamento;
import br.edu.iftm.rastreamento.repository.PacoteRepository;
import br.edu.iftm.rastreamento.repository.RastreamentoRepository;

@Service
public class PacoteService {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private RastreamentoRepository rastreamentoRepository;

    public List<Pacote> getAllPacotes() {
        Iterable<Pacote> pacotesIterable = pacoteRepository.findAll();
        List<Pacote> pacotesList = new ArrayList<>();
        pacotesIterable.forEach(pacotesList::add);
        return pacotesList;
    }

    public Optional<Pacote> getPacoteById(Long id) {
        return pacoteRepository.findById(id);
    }

    public Pacote createPacote(Pacote pacote) {
        return pacoteRepository.save(pacote);
    }

    public Optional<Pacote> updatePacote(Long id, Pacote pacoteDetails) {
        return pacoteRepository.findById(id).map(pacote -> {
            pacote.setId(id);
            pacote.atualizarStatus(pacoteDetails.getStatus(), Date.from(Instant.now()), "não implementado");
            // Obter o último rastreamento
            Rastreamento ultiRastreamento = pacote.getRastreamentos().get(pacote.getRastreamentos().size() - 1);
            rastreamentoRepository.save(ultiRastreamento);
            return pacoteRepository.save(pacote);
        });
    }

    public void deletePacote(Long id) {
        Pacote pacote = pacoteRepository.findById(id).get();
        pacoteRepository.delete(pacote);
    }

    // Método para buscar pacotes por status
    public List<Pacote> findByStatus(String status) {
        return pacoteRepository.findByStatus(status);
    }

    // Método para buscar pacotes por destinatário
    public List<Pacote> findByDestinatario(String destinatario) {
        return pacoteRepository.findByDestinatario(destinatario);
    }

    
}
