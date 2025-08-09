package com.garsite.SmartBuilder.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.garsite.SmartBuilder.Model.Chasis;
import com.garsite.SmartBuilder.Repository.ChasisRepository;

@RestController
@RequestMapping("/api/chasis")
public class ChasisController {

    @Autowired
    private ChasisRepository chasisRepository;

    // Obtener todos los chasis
    @GetMapping
    public List<Chasis> getAll() {
        return chasisRepository.findAll();
    }

    // Obtener chasis por ID
    @GetMapping("/{id}")
    public ResponseEntity<Chasis> getById(@PathVariable Long id) {
        Optional<Chasis> chasis = chasisRepository.findById(id);
        return chasis.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo chasis
    @PostMapping
    public Chasis create(@RequestBody Chasis chasis) {
        return chasisRepository.save(chasis);
    }

    // Actualizar chasis
    @PutMapping("/{id}")
    public ResponseEntity<Chasis> update(@PathVariable Long id, @RequestBody Chasis chasisDetails) {
        return chasisRepository.findById(id)
                .map(chasis -> {
                    chasis.setNombre(chasisDetails.getNombre());
                    chasis.setTipo(chasisDetails.getTipo());
                    chasis.setMaterial(chasisDetails.getMaterial());
                    chasis.setDimensiones(chasisDetails.getDimensiones());
                    chasis.setPeso(chasisDetails.getPeso());
                    chasis.setCapacidadCarga(chasisDetails.getCapacidadCarga());
                    return ResponseEntity.ok(chasisRepository.save(chasis));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar chasis
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (chasisRepository.existsById(id)) {
            chasisRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
