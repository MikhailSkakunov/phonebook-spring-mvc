package ru.sunrise.persist;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.sunrise.persist.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("select distinct p " +
            "from Person p " +
            "join fetch p.address " +
            "left join fetch p.phones")
    List<Person> findAllWithFetch(Sort sort);

    @Query("select distinct p " +
            "from Person p " +
            "join fetch p.address " +
            "left join fetch p.phones " +
            "where p.id = :id")
    Optional<Person> findByIdWithFetch(Long id);

    @Query("select p " +
            "from Person p " +
            " left join p.phones ph " +
            "where (UPPER(p.surname) like UPPER(?1) or ?1 is null) " +
            "  and (UPPER(p.firstName) like UPPER(?2) or ?2 is null) " +
            "  and (UPPER(p.patronymic) like UPPER(?3) or ?3 is null) " +
            "  and (ph.number like ?4 or ?4 is null)")
    List<Person> findByParams(String filterSurname, String filterName, String filterPatronymic, String filterPhone, Sort sort);
}
