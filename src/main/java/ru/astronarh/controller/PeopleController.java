package ru.astronarh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.astronarh.dao.PersonDao;
import ru.astronarh.model.Person;
import ru.astronarh.service.PersonService;

import java.util.List;

@Controller
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personService.index());
        return "people/index";
    }

    @GetMapping("/{personId}")
    public String show(@PathVariable String personId, Model model) {
        Person person = personService.show(personId);

        model.addAttribute("person", person);
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute Person person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute Person person) {
        personService.add(person);

        return "redirect:/people";
    }

    @GetMapping("/{personId}/edit")
    public String edit(@PathVariable String personId, Model model) {
        model.addAttribute("person", personService.show(personId));

        return "people/edit";
    }

    @PatchMapping("/{personId}")
    public String update(@ModelAttribute Person person, @PathVariable String personId) {
        personService.update(personId, person);

        return "redirect:/people";
    }

    @DeleteMapping("/{personId}")
    public String delete(@PathVariable String personId) {
        personService.delete(personId);

        return "redirect:/people";
    }
}
