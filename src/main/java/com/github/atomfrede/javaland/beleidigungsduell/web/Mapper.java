package com.github.atomfrede.javaland.beleidigungsduell.web;

import java.math.BigDecimal;
import java.util.UUID;

import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.Beleidigung;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.BeleidigungUpdate;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.CreateBeleidigung;

public class Mapper {

    public static BeleidigungsDatensatz fromCreateBeleidigung(CreateBeleidigung createBeleidigung) {

        final BeleidigungsDatensatz beleidigungsDatensatz = new BeleidigungsDatensatz(1, UUID.randomUUID(), UUID.randomUUID());
        beleidigungsDatensatz.setBeleidigungs_template(createBeleidigung.getBeleidigungsTemplate());
        beleidigungsDatensatz.setAntwort_template(createBeleidigung.getAntwortTemplate());
        return beleidigungsDatensatz;
    }

    public static BeleidigungsDatensatz fromUpdateBeleidigung(Integer beleidigungId, BeleidigungUpdate beleidigungUpdate) {

        final BeleidigungsDatensatz beleidigungsDatensatz = new BeleidigungsDatensatz(beleidigungId, UUID.randomUUID(), UUID.randomUUID());
        beleidigungsDatensatz.setBeleidigungs_template(beleidigungUpdate.getBeleidigungsTemplate());
        beleidigungsDatensatz.setAntwort_template(beleidigungUpdate.getAntwortTemplate());
        return beleidigungsDatensatz;
    }

    public static Beleidigung toBeleidigung(BeleidigungsDatensatz beleidigungsDatensatz) {
        final Beleidigung beleidigung = new Beleidigung();
        beleidigung.setId(BigDecimal.valueOf(beleidigungsDatensatz.getId()));
        beleidigung.setBeleidigungsId(beleidigungsDatensatz.getBeleidigungsId());
        beleidigung.setAntwortId(beleidigungsDatensatz.getAntwortId());
        beleidigung.setBeleidigungsTemplate(beleidigungsDatensatz.getBeleidigungs_template());
        beleidigung.setAntwortTemplate(beleidigungsDatensatz.getAntwort_template());
        return beleidigung;
    }
}
