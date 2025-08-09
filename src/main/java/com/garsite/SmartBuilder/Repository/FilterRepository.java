package com.garsite.SmartBuilder.Repository;
import com.garsite.SmartBuilder.Model.Filtros;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface FilterRepository extends JpaRepository <Filtros, Long> {
    
    // Buscar por nombre (contiene, sin importar mayúsculas)
    List<Filtros> findByNombreContainingIgnoreCase(String nombre);

    // Buscar por número de parte
    List<Filtros> findByNumeroParteContainingIgnoreCase(String numeroParte);

    // Buscar por tipo de filtro
    List<Filtros> findByTipoFiltroContainingIgnoreCase(String tipoFiltro);

 


}
