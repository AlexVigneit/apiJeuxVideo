package com.waza.apijeuxvideo.repository;

import com.waza.apijeuxvideo.models.Avis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvisRepository extends CrudRepository<Avis, Long> {
}
