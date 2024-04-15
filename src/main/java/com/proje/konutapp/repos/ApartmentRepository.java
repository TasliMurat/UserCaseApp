package com.proje.konutapp.repos;

import com.proje.konutapp.entities.Apartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepository extends CrudRepository<Apartment, Integer> {
    List<Apartment> findByUserId(Long userId);
}
