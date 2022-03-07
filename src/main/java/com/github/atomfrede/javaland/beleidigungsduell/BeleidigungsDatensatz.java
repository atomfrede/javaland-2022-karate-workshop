package com.github.atomfrede.javaland.beleidigungsduell;

import java.util.UUID;

public class BeleidigungsDatensatz {

    private long id;
    private UUID beleidigungsId;
    private UUID antwortId;
    private String beleidigungs_template;
    private String antwort_template;

    public BeleidigungsDatensatz(long id, UUID beleidigungsId, UUID antwortId) {
        this.id = id;
        this.beleidigungsId = beleidigungsId;
        this.antwortId = antwortId;
    }

    public long getId() {
        return id;
    }

    public UUID getBeleidigungsId() {
        return beleidigungsId;
    }

    public UUID getAntwortId() {
        return antwortId;
    }

    public String getBeleidigungs_template() {
        return beleidigungs_template;
    }

    public String getAntwort_template() {
        return antwort_template;
    }

    public void setBeleidigungs_template(String beleidigungs_template) {
        this.beleidigungs_template = beleidigungs_template;
    }

    public void setAntwort_template(String antwort_template) {
        this.antwort_template = antwort_template;
    }
}
