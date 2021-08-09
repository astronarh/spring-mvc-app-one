package ru.astronarh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.astronarh.model.Operation;

@Controller
@RequestMapping("/first")
public class CalculatorController {
    private static final String PAGE_MESSAGE = "Operation: %s between a: %s, and b: %s, result: %s";
    private static final String ATTRIBUTE_MESSAGE = "message";

    @GetMapping("/calculator")
    public String calculate(@RequestParam Double a, @RequestParam Double b, @RequestParam Operation action, Model model) {
        switch (action) {
            case SUBTRACTION:
                model.addAttribute(ATTRIBUTE_MESSAGE, String.format(PAGE_MESSAGE, action, a, b, (a - b)));
                break;
            case ADDITION:
                model.addAttribute(ATTRIBUTE_MESSAGE, String.format(PAGE_MESSAGE, action, a, b, (a + b)));
                break;
            case DIVISION:
                model.addAttribute(ATTRIBUTE_MESSAGE, String.format(PAGE_MESSAGE, action, a, b, (a / b)));
                break;
            case MULTIPLICATION:
                model.addAttribute(ATTRIBUTE_MESSAGE, String.format(PAGE_MESSAGE, action, a, b, (a * b)));
                break;
        }

        return "first/calculator";
    }
}
