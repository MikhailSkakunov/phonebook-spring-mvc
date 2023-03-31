package ru.sunrise.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sunrise.persist.model.PhoneType;

import java.util.Optional;

public interface PhoneTypeRepository extends JpaRepository<PhoneType, Long> {

    Optional<PhoneType> findByTypeName(String name);
}
