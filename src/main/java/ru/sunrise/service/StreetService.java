package ru.sunrise.service;

import org.springframework.data.domain.Sort;
import ru.sunrise.persist.model.Street;

import java.util.List;
import java.util.Optional;

public interface StreetService {

    void deleteById(long id);

    Optional<Street> findById(long id);

    List<Street> findAll(Sort streetName);

    void save(Street street);

}
