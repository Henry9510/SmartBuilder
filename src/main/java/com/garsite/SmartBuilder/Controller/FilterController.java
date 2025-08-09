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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.garsite.SmartBuilder.Model.Filtros;
import com.garsite.SmartBuilder.Repository.FilterRepository;

@RestController
@RequestMapping("/api/filtros")
public class FilterController {

    @Autowired
    private FilterRepository filtrosRepository;

    // Obtener todos los filtros
    @GetMapping
    public List<Filtros> getAll() {
        return filtrosRepository.findAll();
    }

    // Obtener filtro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Filtros> getById(@PathVariable Long id) {
        Optional<Filtros> filtro = filtrosRepository.findById(id);
        return filtro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo filtro
    @PostMapping
    public Filtros create(@RequestBody Filtros filtro) {
        return filtrosRepository.save(filtro);
    }

    // Actualizar filtro
    @PutMapping("/{id}")
    public ResponseEntity<Filtros> update(@PathVariable Long id, @RequestBody Filtros filtroDetails) {
        return filtrosRepository.findById(id)
                .map(filtro -> {
                    filtro.setCaudal(filtroDetails.getCaudal());
                    filtro.setTipoFiltro(filtroDetails.getTipoFiltro());
                    filtro.setTipoMaterial(filtroDetails.getTipoMaterial());
                    filtro.setTipoMontaje(filtroDetails.getTipoMontaje());
                    filtro.setTipoConexiones(filtroDetails.getTipoConexiones());
                    filtro.setNumeroParte(filtroDetails.getNumeroParte());
                    filtro.setNombre(filtroDetails.getNombre());
                    filtro.setDiferenciaPresion(filtroDetails.getDiferenciaPresion());
                    return ResponseEntity.ok(filtrosRepository.save(filtro));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar filtro
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (filtrosRepository.existsById(id)) {
            filtrosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //  Buscar por nombre
    @GetMapping("/buscar/nombre")
    public List<Filtros> buscarPorNombre(@RequestParam String nombre) {
        return filtrosRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar por número de parte
    @GetMapping("/buscar/parte")
    public List<Filtros> buscarPorNumeroParte(@RequestParam String numeroParte) {
        return filtrosRepository.findByNumeroParteContainingIgnoreCase(numeroParte);
    }

    // Buscar por tipo de filtro
    @GetMapping("/buscar/tipo")
    public List<Filtros> buscarPorTipo(@RequestParam String tipo) {
        return filtrosRepository.findByTipoFiltroContainingIgnoreCase(tipo);
    }
}