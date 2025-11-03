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
Matias
TypesControllerText
package com.tecsup.petclinic.webs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tecsup.petclinic.entities.Type;
import com.tecsup.petclinic.services.TypeService;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TypesController.class)
public class TypesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TypeService typeService;

    private List<Type> mockTypes;

    @BeforeEach
    void setUp() {
        Type cat = new Type();
        cat.setId(1L);
        cat.setName("cat");

        Type dog = new Type();
        dog.setId(2L);
        dog.setName("dog");

        mockTypes = Arrays.asList(cat, dog);
    }

    @Test
    void testListTypes() throws Exception {
        given(typeService.findAll()).willReturn(mockTypes);

        mockMvc.perform(get("/types"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("types"))
                .andExpect(view().name("types/index"));
    }
}
