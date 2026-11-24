package com.exemplo.notasia.repository;

import com.exemplo.notasia.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
}
