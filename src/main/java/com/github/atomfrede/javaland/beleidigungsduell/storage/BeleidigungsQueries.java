package com.github.atomfrede.javaland.beleidigungsduell.storage;

import static com.github.atomfrede.javaland.beleidigungsduell.gen.jooq.Tables.BELEIDIGUNG;

import java.util.List;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import static com.github.atomfrede.javaland.beleidigungsduell.gen.jooq.Tables.BELEIDIGUNG;
import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;

@Component
public class BeleidigungsQueries {

    private final DSLContext jooq;

    public BeleidigungsQueries(DSLContext jooq) {

        this.jooq = jooq;
    }

    public void delete(Long id) {

        jooq.deleteFrom(BELEIDIGUNG).where(BELEIDIGUNG.ID.eq(id)).execute();
    }

    public BeleidigungsDatensatz find(Long id) {

        return jooq.selectFrom(BELEIDIGUNG).where(BELEIDIGUNG.ID.eq(id))
            .fetchOptional().map(this::mapBeleidigungsDatensatz).orElse(null);
    }

    public List<BeleidigungsDatensatz> list(Integer limit, Integer offset) {
        return jooq.selectFrom(BELEIDIGUNG).limit(limit).offset(offset)
            .fetch().map(this::mapBeleidigungsDatensatz);
    }

    public Long count() {
        return Integer.valueOf(jooq.fetchCount(BELEIDIGUNG)).longValue();
    }

    public BeleidigungsDatensatz update(BeleidigungsDatensatz beleidigungsDatensatz) {

        return jooq.update(BELEIDIGUNG)
            .set(BELEIDIGUNG.BELEIDIGUNGS_TEMPLATE, beleidigungsDatensatz.getBeleidigungs_template())
            .set(BELEIDIGUNG.ANTWORT_TEMPLATE, beleidigungsDatensatz.getAntwort_template())
            .where(BELEIDIGUNG.ID.eq(beleidigungsDatensatz.getId()))
            .returning()
            .fetchOne()
            .map(this::mapBeleidigungsDatensatz);
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

    private BeleidigungsDatensatz mapBeleidigungsDatensatz(org.jooq.Record r) {

        BeleidigungsDatensatz result =
            new BeleidigungsDatensatz(r.get(BELEIDIGUNG.ID), r.get(BELEIDIGUNG.BELEIDIGUNGS_ID), r.get(BELEIDIGUNG.ANTWORT_ID));
        result.setBeleidigungs_template(r.get(BELEIDIGUNG.BELEIDIGUNGS_TEMPLATE));
        result.setBeleidigungs_template(r.get(BELEIDIGUNG.ANTWORT_TEMPLATE));
        return result;
    }
}
