package com.github.atomfrede.javaland.beleidigungsduell.storage;

import com.github.atomfrede.javaland.beleidigungsduell.AbstractJooqTest;
import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class BeleidigungsQueriesTest extends AbstractJooqTest {

    static BeleidigungsQueries subject;

    @BeforeAll
    public static void beforeAll() {
        subject = new BeleidigungsQueries(jooq);
    }

    @Test
    void shouldFindRandom() {

        BeleidigungsDatensatz random = subject.random();
        BeleidigungsDatensatz random1 = subject.random();

        Assertions.assertThat(random.getId()).isNotEqualTo(random1.getId());
    }
}