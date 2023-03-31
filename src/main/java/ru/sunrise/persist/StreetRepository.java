package ru.sunrise.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sunrise.persist.model.Street;

import java.util.Optional;

public interface StreetRepository extends JpaRepository<Street, Long> {

    Optional<Street> findByStreetName(String name);
}
