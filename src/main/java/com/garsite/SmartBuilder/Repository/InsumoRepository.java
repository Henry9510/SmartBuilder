package com.garsite.SmartBuilder.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.garsite.SmartBuilder.Model.Insumos;

@Repository
public interface InsumoRepository  extends JpaRepository<Insumos, Long>     {

       @Query("""
           SELECT i FROM Insumos i
           WHERE (:q IS NULL OR :q = '' 
              OR LOWER(i.codigoInsumo) LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(i.numero_parte) LIKE LOWER(CONCAT('%', :q, '%'))
              OR LOWER(i.descripcion) LIKE LOWER(CONCAT('%', :q, '%'))
           )
           """)
    Page<Insumos> search(@Param("q") String q, Pageable pageable);



}
