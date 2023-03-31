package ru.sunrise.controllers.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PersonWithAddressDto {

    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String surname;

    @NotBlank
    private String patronymic;

    @NotBlank
    private String city;

    private Long streetId;

    @NotBlank
    private String buildingNumber;

    private List<PhoneDto> phones = new ArrayList<>();
}
