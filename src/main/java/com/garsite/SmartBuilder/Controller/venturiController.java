package com.garsite.SmartBuilder.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.garsite.SmartBuilder.Model.Venturi;
import com.garsite.SmartBuilder.Repository.VenturiRepository;

@RestController
@RequestMapping("api/venturi")
@CrossOrigin(origins = "*")
public class venturiController {
    @Autowired
    private VenturiRepository venturiRepository;

    // Crear un nuevo Venturi
    @PostMapping
    public ResponseEntity<Venturi> crearVenturi(@RequestBody Venturi venturi) {
        Venturi guardado = venturiRepository.save(venturi);
        return ResponseEntity.ok(guardado);
    }

    // Obtener todos los Venturis
    @GetMapping
    public List<Venturi> listarVenturis() {
        return venturiRepository.findAll();

    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venturi> obtenerPorId(@PathVariable Long id) {
        Optional<Venturi> venturi = venturiRepository.findById(id);
        return venturi.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Actualizar Venturi
    @PutMapping("/{id}")
    public ResponseEntity<Venturi> actualizarVenturi(@PathVariable Long id, @RequestBody Venturi datos) {
        return venturiRepository.findById(id).map(venturi -> {
            venturi.setCodigo(datos.getCodigo());
            venturi.setNumeroParte(datos.getNumeroParte());
            venturi.setDescripcion(datos.getDescripcion());
            venturi.setMaxFlow(datos.getMaxFlow());
            Venturi actualizado = venturiRepository.save(venturi);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar Venturi
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarVenturi(@PathVariable Long id) {
        if (venturiRepository.existsById(id)) {
            venturiRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
