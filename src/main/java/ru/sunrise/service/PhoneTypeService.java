package ru.sunrise.service;

import org.springframework.data.domain.Sort;
import ru.sunrise.persist.model.PhoneType;

import java.util.List;
import java.util.Optional;

public interface PhoneTypeService {

    List<PhoneType> findAll(Sort phoneType);

    Optional<PhoneType> findById(long id);

    void save(PhoneType phoneType);

    void deleteById(long id);
}
