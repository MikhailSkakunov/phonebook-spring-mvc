package ru.sunrise.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sunrise.util.NotFoundException;
import ru.sunrise.persist.StreetRepository;
import ru.sunrise.persist.model.Address;
import ru.sunrise.persist.model.Street;
import ru.sunrise.util.AlreadyExistException;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class StreetServiceImpl implements StreetService {

    private final StreetRepository streetRepository;

    @Autowired
    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
    }

    @Override
    public Optional<Street> findById(long id) {
        Optional<Street> street;
        if ((street = streetRepository.findById(id)).isEmpty()) {
            throw new NotFoundException("No street with id " + id);
        } else {
            return street;
        }
    }

    @Override
    public List<Street> findAll(Sort streetName) {
            return streetRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Street street) {
        if (streetRepository.findByStreetName(street.getStreetName()).isPresent()) {
            throw new AlreadyExistException("A street with the same name already exists.");
        } else {
            streetRepository.save(street);
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        List<Address> addresses = streetRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No street with id " + id))
                .getAddresses();
        for (Address a:addresses) {
            a.setStreet(null);
        }
        streetRepository.deleteById(id);
    }
}
