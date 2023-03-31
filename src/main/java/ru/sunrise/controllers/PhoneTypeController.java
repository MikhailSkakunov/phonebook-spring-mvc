package ru.sunrise.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sunrise.persist.model.PhoneType;
import ru.sunrise.service.PhoneTypeService;

@Slf4j
@RequestMapping("/phoneType")
@Controller
public class PhoneTypeController {

    private final PhoneTypeService phoneTypeService;

    @Autowired
    public PhoneTypeController(PhoneTypeService phoneTypeService) {
        this.phoneTypeService = phoneTypeService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("phoneTypes", phoneTypeService.findAll(Sort.by("typeName")));
        return "phone_types";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("phoneType", new PhoneType());
        return "phone_type_form";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("phoneType", phoneTypeService.findById(id));
        return "phone_type_form";
    }

    @PostMapping
    public String save(@Valid PhoneType phoneType, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "phone_type_form";
        } else {
            phoneTypeService.save(phoneType);
            return "redirect:/phoneType";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        phoneTypeService.deleteById(id);
        return "redirect:/phoneType";
    }
}
