package org.gfg.JBDL49L10.repository;

import org.gfg.JBDL49L10.model.MyPerson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<MyPerson, Integer> {
}
