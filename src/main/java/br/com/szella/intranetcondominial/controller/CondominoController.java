package br.com.szella.intranetcondominial.controller;

import br.com.szella.intranetcondominial.modal.mapper.CondominoMapper;
import br.com.szella.intranetcondominial.modal.request.CondominoEditarRequest;
import br.com.szella.intranetcondominial.modal.request.CondominoSalvarRequest;
import br.com.szella.intranetcondominial.modal.response.CondominoResponse;
import br.com.szella.intranetcondominial.service.CondominoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/v1/condominos")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class CondominoController {
    private final CondominoService service;

    @GetMapping
    public ResponseEntity<List<CondominoResponse>> listar() {
        var entities = service.listar();
        return ResponseEntity.ok(CondominoMapper.mapListaResponse(entities));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CondominoResponse> buscarPorId(@PathVariable Long id) {
        var entity = Optional
                .ofNullable(service.buscarPorId(id))
                .filter(Objects::nonNull)
                .map(CondominoMapper::mapResponse)
                .orElse(null);

        return ResponseEntity.ok(entity);
    }

    @PostMapping
    public ResponseEntity<CondominoResponse> salvar(@RequestBody CondominoSalvarRequest request) {
        var entity = service.salvar(request);
        return ResponseEntity.ok(CondominoMapper.mapResponse(entity));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CondominoResponse> editar(@PathVariable Long id, @RequestBody CondominoEditarRequest request) {
        var entity = service.editar(id, request);
        return ResponseEntity.ok(CondominoMapper.mapResponse(entity));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> editar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok().build();
    }
}
