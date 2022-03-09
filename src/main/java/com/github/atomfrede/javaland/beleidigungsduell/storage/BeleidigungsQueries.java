package com.github.atomfrede.javaland.beleidigungsduell.storage;

import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import static com.github.atomfrede.javaland.beleidigungsduell.gen.jooq.Tables.BELEIDIGUNG;

@Component
public class BeleidigungsQueries {

    private final DSLContext jooq;

    public BeleidigungsQueries(DSLContext jooq) {
        this.jooq = jooq;
    }

    public BeleidigungsDatensatz create(BeleidigungsDatensatz beleidigungsDatensatz) {

        return jooq.insertInto(BELEIDIGUNG)
                .set(BELEIDIGUNG.BELEIDIGUNGS_TEMPLATE, beleidigungsDatensatz.getBeleidigungs_template())
                .set(BELEIDIGUNG.ANTWORT_TEMPLATE, beleidigungsDatensatz.getAntwort_template())
                .returning()
                .fetchOne()
                .map(r -> {
                    BeleidigungsDatensatz result = new BeleidigungsDatensatz(r.get(BELEIDIGUNG.ID), r.get(BELEIDIGUNG.BELEIDIGUNGS_ID), r.get(BELEIDIGUNG.ANTWORT_ID));
                    result.setBeleidigungs_template(r.get(BELEIDIGUNG.BELEIDIGUNGS_TEMPLATE));
                    result.setAntwort_template(r.get(BELEIDIGUNG.ANTWORT_TEMPLATE));
                    return result;
                });

    }

    public BeleidigungsDatensatz random() {

        return jooq.selectFrom(BELEIDIGUNG)
                .orderBy(DSL.rand())
                .limit(1)
                .fetchOne()
                .map(r -> {
                            BeleidigungsDatensatz result = new BeleidigungsDatensatz(r.get(BELEIDIGUNG.ID), r.get(BELEIDIGUNG.BELEIDIGUNGS_ID), r.get(BELEIDIGUNG.ANTWORT_ID));
                            result.setBeleidigungs_template(r.get(BELEIDIGUNG.BELEIDIGUNGS_TEMPLATE));
                            result.setAntwort_template(r.get(BELEIDIGUNG.ANTWORT_TEMPLATE));
                            return result;
                        }
                );
    }
}
