package ru.sunrise.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sunrise.persist.model.PhoneType;
import ru.sunrise.util.AlreadyExistException;
import ru.sunrise.util.NotFoundException;
import ru.sunrise.persist.PhoneTypeRepository;
import ru.sunrise.persist.model.Phone;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class PhoneTypeServiceImpl implements PhoneTypeService {

    private final PhoneTypeRepository phoneTypeRepository;

    @Autowired
    public PhoneTypeServiceImpl(PhoneTypeRepository phoneTypeRepository) {
        this.phoneTypeRepository = phoneTypeRepository;
    }

    @Override
    public List<PhoneType> findAll(Sort phoneType) {
            return phoneTypeRepository.findAll();
    }

    @Override
    public Optional<PhoneType> findById(long id) {
        Optional<PhoneType> phoneType;
        if ((phoneType = phoneTypeRepository.findById(id)).isEmpty()) {
            throw new NotFoundException("No phoneType with id " + id);
        } else {
            return phoneType;
        }
    }

    @Override
    @Transactional
    public void save(PhoneType phoneType) {
        if (phoneTypeRepository.findByTypeName(phoneType.getTypeName()).isPresent()) {
            throw new AlreadyExistException("A phoneType with the same name already exists.");
        } else {
            phoneTypeRepository.save(phoneType);
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        List<Phone> phones = phoneTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No phoneType with id " + id))
                .getPhones();
        for (Phone p : phones) {
            p.setPhoneType(null);
        }
        phoneTypeRepository.deleteById(id);
    }
}
