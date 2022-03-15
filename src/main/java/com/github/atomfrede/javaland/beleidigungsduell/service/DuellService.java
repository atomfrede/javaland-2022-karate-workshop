package com.github.atomfrede.javaland.beleidigungsduell.service;

import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import com.github.atomfrede.javaland.beleidigungsduell.external.LosSchimpfosApi;
import com.github.atomfrede.javaland.beleidigungsduell.storage.BeleidigungsQueries;
import io.vavr.Tuple;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.tuple.Triple;
import org.hibernate.validator.internal.metadata.aggregated.ExecutableMetaData;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class DuellService {


    private final BeleidigungsQueries queries;
    private final LosSchimpfosApi losSchimpfosApi;

    public DuellService(BeleidigungsQueries queries, LosSchimpfosApi losSchimpfosApi) {
        this.queries = queries;
        this.losSchimpfosApi = losSchimpfosApi;
    }

    public boolean win(UUID antwortId, UUID beleididgungsId) {

        BeleidigungsDatensatz beleidigungsDatensatz = this.queries.findByBeleidigungsId(beleididgungsId);
        if (Objects.nonNull(beleidigungsDatensatz) && beleidigungsDatensatz.getAntwortId().equals(antwortId)) {
            return true;
        }
        return false;
    }

    public BeleidigungsDatensatz getNext() {

        Triple<String, String, String> random3x = losSchimpfosApi.getRandom3x();

        return queries.random();
    }

    public Mono<BeleidigungsDatensatz> getNextMono() {

        return Mono.just(queries.random());
    }

    public Triple<BeleidigungsDatensatz, BeleidigungsDatensatz, BeleidigungsDatensatz> getNext3x(){

        Pair<String, String> first = losSchimpfosApi.getRandomTuple();
        Pair<String, String> second = losSchimpfosApi.getRandomTuple();
        Pair<String, String> third = losSchimpfosApi.getRandomTuple();

        List<BeleidigungsDatensatz> beleidigungsDaten = Flux.merge(getNextMono(),
                        getNextMono(),
                        getNextMono())
                .zipWith(Flux.just(first, second, third), (datensatz,pair) ->
                        datensatz.fillBeleidigungsTemplate(NSFWFilter.filter(pair.getLeft())).fillAntwortTemplate(NSFWFilter.filter(pair.getRight())))
                .collectList()
                .block();

        return Triple.of(beleidigungsDaten.get(0), beleidigungsDaten.get(1), beleidigungsDaten.get(2));
    }
}
