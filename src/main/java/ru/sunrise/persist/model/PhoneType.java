package ru.sunrise.persist.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone_types")
public class PhoneType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_name", nullable = false)
    @NotBlank
    private String typeName;

    @OneToMany(mappedBy = "phoneType", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<Phone> phones = new ArrayList<>();
}
