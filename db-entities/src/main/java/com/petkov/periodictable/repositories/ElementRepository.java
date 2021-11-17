package com.petkov.periodictable.repositories;

import com.petkov.periodictable.entities.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ElementRepository extends JpaRepository<Element, Long>,
        JpaSpecificationExecutor<Element> {

}
