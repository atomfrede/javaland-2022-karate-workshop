package com.github.atomfrede.javaland.beleidigungsduell.web;

import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import com.github.atomfrede.javaland.beleidigungsduell.service.DuellService;
import io.vavr.control.Try;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Controller("/")
public class IndexController {

    private final DuellService duellService;
    private final Random random;

    public IndexController(DuellService duellService) {
        this.duellService = duellService;
        random = new Random(128);
    }

    private UUID tryParse(String fixed) {
       return Try.ofSupplier(() -> UUID.fromString(fixed)).getOrNull();
    }
    @GetMapping
    public String getIndex(Model model, @RequestParam(required = false) String fixedId) {

        if (StringUtils.isNotBlank(fixedId) && Objects.nonNull(tryParse(fixedId))) {
            BeleidigungsDatensatz fixed = duellService.getFixed(UUID.fromString(fixedId));
            BeleidigungsDatensatz second = duellService.getNext();
            BeleidigungsDatensatz third = duellService.getNext();

            model.addAttribute("won", false);
            model.addAttribute("first", fixed);
            model.addAttribute("second", second);
            model.addAttribute("third", third);

            model.addAttribute("start", fixed);

            return "index";
        } else {

            Triple<BeleidigungsDatensatz, BeleidigungsDatensatz, BeleidigungsDatensatz> next3x = duellService.getNext3x();

            model.addAttribute("won", false);
            model.addAttribute("first", next3x.getLeft());
            model.addAttribute("second", next3x.getMiddle());
            model.addAttribute("third", next3x.getRight());

            List<BeleidigungsDatensatz> all = Arrays.asList(next3x.getLeft(), next3x.getMiddle(), next3x.getRight());
            Collections.shuffle(all);

            model.addAttribute("start", all.get(random.nextInt(3)));
            return "index";
        }
    }

    @PostMapping
    public String postIndex(String beleidigung, String antwort, Model model) {

        UUID beleidigungsId = UUID.fromString(beleidigung);
        UUID antwortId = UUID.fromString(antwort);

        boolean hasWon = duellService.win(antwortId, beleidigungsId);

        Triple<BeleidigungsDatensatz, BeleidigungsDatensatz, BeleidigungsDatensatz> next3x = duellService.getNext3x();

        model.addAttribute("won", hasWon);
        model.addAttribute("first", next3x.getLeft());
        model.addAttribute("second", next3x.getMiddle());
        model.addAttribute("third", next3x.getRight());

        List<BeleidigungsDatensatz> all = Arrays.asList(next3x.getLeft(), next3x.getMiddle(), next3x.getRight());
        Collections.shuffle(all);

        model.addAttribute("start", all.get(random.nextInt(3)));

        return "index";
    }
}
