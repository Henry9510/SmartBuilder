package com.garsite.SmartBuilder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.garsite.SmartBuilder.Model.Chasis;

@Repository
public interface ChasisRepository extends JpaRepository<Chasis, Long> {

    // Additional query methods can be defined here if needed       

}
