package ru.sunrise.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sunrise.persist.model.Street;
import ru.sunrise.service.StreetService;


@Slf4j
@RequestMapping("/street")
@Controller
public class StreetController {

    private final StreetService streetService;

    @Autowired
    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("streets", streetService.findAll(Sort.by("streetName")));
        return "streets";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("street", new Street());
        return "street_form";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable long id, Model model) {
        model.addAttribute("street", streetService.findById(id));
        return "street_form";
    }

    @PostMapping
    public String save(@Valid Street street, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "street_form";
        }
        streetService.save(street);
        return "redirect:/street";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        streetService.deleteById(id);
        return "redirect:/street";
    }
}
