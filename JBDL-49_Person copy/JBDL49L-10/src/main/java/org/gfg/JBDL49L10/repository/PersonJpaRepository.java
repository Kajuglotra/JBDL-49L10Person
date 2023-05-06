package org.gfg.JBDL49L10.repository;

import org.gfg.JBDL49L10.model.Myperson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<Myperson, Integer> {
}
