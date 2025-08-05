package com.garsite.SmartBuilder.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garsite.SmartBuilder.Model.Venturi;

public interface VenturiRepository extends JpaRepository<Venturi, Long> {

    Optional<Venturi> findByCodigo(String codigo);
    Optional<Venturi> findByNumeroParte(String numeroParte);
    List<Venturi> findByMaxFlowGreaterThanEqual(Integer minFlow);
    List<Venturi> findByDescripcionContainingIgnoreCase(String keyword);

}
