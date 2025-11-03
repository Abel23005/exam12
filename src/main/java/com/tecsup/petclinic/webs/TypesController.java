package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.services.TypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TypesController {

    private final TypeService typeService;

    public TypesController(TypeService typeService) {
        this.typeService = typeService;
    }

    @GetMapping("/types")
    public String listTypes(Model model) {
        List<Type> types = typeService.findAll();
        model.addAttribute("types", types);
        return "types/index";
    }
}
: