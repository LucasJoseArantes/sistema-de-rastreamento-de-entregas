package br.edu.iftm.rastreamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iftm.rastreamento.dto.EnderecoDTO;
import br.edu.iftm.rastreamento.service.EnderecoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/enderecos")
@Tag(name = "Addresses", description = "Endpoint for managing addresses.")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    @Operation(summary = "Get all addresses", description = "Returns a list of all addresses", responses = {
            @ApiResponse(description = "OK", responseCode = "200", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    public List<EnderecoDTO> getAllEnderecos() {
        return enderecoService.getAllEnderecos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get address by ID", description = "Finds an address by its ID", responses = {
            @ApiResponse(description = "Found", responseCode = "302", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> getById(@PathVariable Long id) {
        EnderecoDTO endereco = enderecoService.getEnderecoById(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(endereco);
    }

    @PostMapping
    @Operation(summary = "Create a new address", description = "Creates a new address", responses = {
            @ApiResponse(description = "Created", responseCode = "201", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content)
    })
    public ResponseEntity<EnderecoDTO> createEndereco(@RequestBody EnderecoDTO enderecoDTO) {
        EnderecoDTO novoRecursEnderecoDTO = enderecoService.createEndereco(enderecoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoRecursEnderecoDTO);
    }
}

