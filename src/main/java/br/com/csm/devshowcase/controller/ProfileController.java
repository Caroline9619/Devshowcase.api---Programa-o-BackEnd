package br.com.csm.devshowcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.csm.devshowcase.dto.ProfileRequestDTO;
import br.com.csm.devshowcase.dto.ProfileResponseDTO;
import br.com.csm.devshowcase.service.ProfileService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    @PostMapping
    public ResponseEntity<ProfileResponseDTO> cadastrar(
            @Valid @RequestBody ProfileRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.salvar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponseDTO> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }
}