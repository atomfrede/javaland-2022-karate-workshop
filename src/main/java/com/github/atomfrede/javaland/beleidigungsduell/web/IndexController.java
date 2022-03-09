package com.github.atomfrede.javaland.beleidigungsduell.web;

import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import com.github.atomfrede.javaland.beleidigungsduell.service.DuellService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Controller("/")
public class IndexController {

    private final DuellService duellService;
    private final Random random;

    public IndexController(DuellService duellService) {
        this.duellService = duellService;
        random = new Random(128);
    }

    @GetMapping
    public String index(Model model) {

        BeleidigungsDatensatz first = duellService.getNext();
        BeleidigungsDatensatz second = duellService.getNext();
        BeleidigungsDatensatz third = duellService.getNext();

        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("third", third);

        List<BeleidigungsDatensatz> all = Arrays.asList(first, second, third);
        Collections.shuffle(all);

        model.addAttribute("start", all.get(random.nextInt(3)));

        return "index";
    }
}
