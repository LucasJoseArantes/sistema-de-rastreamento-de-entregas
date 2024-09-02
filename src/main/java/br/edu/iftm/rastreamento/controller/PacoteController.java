package br.edu.iftm.rastreamento.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import br.edu.iftm.rastreamento.model.Pacote;
import br.edu.iftm.rastreamento.service.PacoteService;
import br.edu.iftm.rastreamento.service.exceptions.NaoAcheiException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/pacotes")
@Tag(name = "Packages", description = "Endpoint for managing packages.")
public class PacoteController {

    @Autowired
    private PacoteService pacoteService;

    @GetMapping
    @Operation(summary = "Get all packages", description = "Returns a list of all packages", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public List<Pacote> getAllPacotes() {
        return pacoteService.getAllPacotes();
    }

    @PostMapping
    @Operation(summary = "Create a new package", description = "Creates a new package", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content)
    })
    public Pacote createPacote(@RequestBody Pacote pacote) {
        return pacoteService.createPacote(pacote);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get package by ID", description = "Finds a package by its ID", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public Pacote getPacoteById(@PathVariable Long id) {
        Pacote pacote = pacoteService.getPacoteById(id).orElseThrow(
            () -> new NaoAcheiException("Pacote não encontrado"));
        return pacote;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a package", description = "Updates a package by its ID", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content)
    })
    public Pacote updatePacote(@PathVariable Long id, @RequestBody Pacote pacoteDetails) {
        Pacote pacote = pacoteService.updatePacote(id, pacoteDetails).orElseThrow(
            () -> new NaoAcheiException("Pacote não encontrado"));
        return pacote;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a package", description = "Deletes a package by its ID", responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public void deletePacote(@PathVariable Long id) {
        pacoteService.deletePacote(id);
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get packages by status", description = "Finds packages by status", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public List<Pacote> getPacotesByStatus(@PathVariable String status) {
        return pacoteService.findByStatus(status);
    }

    @GetMapping("/destinatario/{destinatario}")
    @Operation(summary = "Get packages by recipient", description = "Finds packages by recipient", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public List<Pacote> getPacotesByDestinatario(@PathVariable String destinatario) {
        return pacoteService.findByDestinatario(destinatario);
    }
}
