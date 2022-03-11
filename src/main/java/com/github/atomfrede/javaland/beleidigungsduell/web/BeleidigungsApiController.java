package com.github.atomfrede.javaland.beleidigungsduell.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.github.atomfrede.javaland.beleidigungsduell.BeleidigungsDatensatz;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.BeleidigungenApi;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.Beleidigung;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.BeleidigungUpdate;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.Beleidigungen;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.BeleidigungenParams;
import com.github.atomfrede.javaland.beleidigungsduell.api.admin.model.CreateBeleidigung;
import com.github.atomfrede.javaland.beleidigungsduell.storage.BeleidigungsQueries;

@Controller
public class BeleidigungsApiController implements BeleidigungenApi {

    private final BeleidigungsQueries beleidigungsQueries;

    public BeleidigungsApiController(BeleidigungsQueries beleidigungsQueries) {

        this.beleidigungsQueries = beleidigungsQueries;
    }

    @Override public ResponseEntity<Beleidigung> createBeleidigung(CreateBeleidigung createBeleidigung) {

        BeleidigungsDatensatz beleidigungsDatensatz = Mapper.fromCreateBeleidigung(createBeleidigung);
        final BeleidigungsDatensatz update = this.beleidigungsQueries.create(beleidigungsDatensatz);
        return ResponseEntity.ok(Mapper.toBeleidigung(update));
    }

    @Override public ResponseEntity<Beleidigungen> listBeleidigungen(Integer limit, Integer offset) {

        final List<BeleidigungsDatensatz> list = this.beleidigungsQueries.list(limit, offset);
        final Long count = this.beleidigungsQueries.count();

        final BeleidigungenParams params = new BeleidigungenParams();
        params.setLimit(limit);
        params.setOffset(offset);

        final Beleidigungen beleidigungen = new Beleidigungen();
        beleidigungen.setParams(params);
        beleidigungen.setItems(list.stream().map(Mapper::toBeleidigung).collect(Collectors.toList()));
        beleidigungen.setTotalCount(count);

        return ResponseEntity.ok(beleidigungen);
    }

    @Override public ResponseEntity<Void> deleteBeleidigung(Integer beleidigungId) {

        this.beleidigungsQueries.delete(Long.valueOf(beleidigungId));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override public ResponseEntity<Beleidigung> readBeleidigung(Integer beleidigungId) {

        final BeleidigungsDatensatz beleidigungsDatensatz = this.beleidigungsQueries.find(beleidigungId.longValue());
        if (beleidigungsDatensatz != null) {
            return ResponseEntity.ok(Mapper.toBeleidigung(beleidigungsDatensatz));
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override public ResponseEntity<Void> updateBeleidigung(Integer beleidigungId, BeleidigungUpdate beleidigungUpdate) {

        BeleidigungsDatensatz beleidigungsDatensatz = Mapper.fromUpdateBeleidigung(beleidigungId, beleidigungUpdate);
        this.beleidigungsQueries.update(beleidigungsDatensatz);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
