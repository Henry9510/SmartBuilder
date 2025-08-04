package com.garsite.SmartBuilder.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garsite.SmartBuilder.Model.Insumos;


@Repository
public interface RepositoryItem extends JpaRepository<Insumos, Long> {

}
