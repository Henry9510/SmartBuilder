package com.garsite.SmartBuilder.Controller;

import com.garsite.SmartBuilder.Model.Insumos;
import com.garsite.SmartBuilder.Repository.InsumoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/insumos")
@CrossOrigin // quítalo o ajusta orígenes según necesites
public class InsumosController {

    @Autowired
    private InsumoRepository repo;

    // LISTAR (paginado) + búsqueda opcional ?q=texto
    @GetMapping
    public Page<Insumos> list(
            @RequestParam(required = false) String q,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "codigo_insumo,asc") String sort
    ) {
        Sort s = Sort.by(sort.split(",")[0]).ascending();
        if (sort.endsWith(",desc")) s = s.descending();
        Pageable pageable = PageRequest.of(page, size, s);
        return repo.search(q, pageable);
    }

    // OBTENER POR ID
    @GetMapping("/{id}")
    public Insumos get(@PathVariable Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Insumo no encontrado"));
    }

    // CREAR
    @PostMapping
    public ResponseEntity<Insumos> create(@RequestBody Insumos body) {
        // opcional: evitar que envíen ID
        body.setId(null);
        Insumos saved = repo.save(body);
        return ResponseEntity
                .created(URI.create("/api/insumos/" + saved.getId()))
                .body(saved);
    }

    // ACTUALIZAR (reemplazo completo)
    @PutMapping("/{id}")
    public Insumos update(@PathVariable Long id, @RequestBody Insumos body) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Insumo no encontrado");
        }
        body.setId(id);
        return repo.save(body);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Insumo no encontrado");
        }
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
