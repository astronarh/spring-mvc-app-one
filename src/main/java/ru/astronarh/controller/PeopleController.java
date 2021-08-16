package ru.astronarh.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.astronarh.model.Person;
import ru.astronarh.service.PersonService;

import java.sql.SQLException;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public String index(Model model) throws SQLException {
        model.addAttribute("people", personService.index());
        return "people/index";
    }

    @GetMapping("/{personId}")
    public String show(@PathVariable String personId, Model model) throws SQLException {
        Person person = personService.show(personId);

        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute @Valid Person person, BindingResult bindingResult) throws SQLException {
        if(bindingResult.hasErrors()) {
            return "people/new";
        }

        personService.add(person);

        return "redirect:/people";
    }

    @GetMapping("/{personId}/edit")
    public String edit(@PathVariable String personId, Model model) throws SQLException {
        model.addAttribute("person", personService.show(personId));

        return "people/edit";
    }

    @PostMapping("/{personId}")
    public String update(@ModelAttribute @Valid Person person, BindingResult bindingResult, @PathVariable String personId) throws SQLException {
        if(bindingResult.hasErrors()) {
            return "people/edit";
        }

        personService.update(personId, person);

        return "redirect:/people";
    }

    @PostMapping("/{personId}/delete")
    public String delete(@PathVariable String personId) throws SQLException {
        personService.delete(personId);

        return "redirect:/people";
    }
}
