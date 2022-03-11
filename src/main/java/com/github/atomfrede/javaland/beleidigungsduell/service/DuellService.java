package com.github.atomfrede.javaland.beleidigungsduell.service;

import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import com.github.atomfrede.javaland.beleidigungsduell.storage.BeleidigungsQueries;
import org.springframework.stereotype.Service;

@Service
public class DuellService {

    private final BeleidigungsQueries queries;

    public DuellService(BeleidigungsQueries queries) {
        this.queries = queries;
    }

    public BeleidigungsDatensatz getNext() {

        return queries.random();
    }
}
