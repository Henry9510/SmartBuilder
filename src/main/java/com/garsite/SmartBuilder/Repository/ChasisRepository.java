package com.garsite.SmartBuilder.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.garsite.SmartBuilder.Model.Chasis;

public interface ChasisRepository extends JpaRepository<Chasis, Long> {

    // Additional query methods can be defined here if needed       

}
