package org.maintenancesystem2.model;

import java.util.Date;

public class EntryNote {
    private Long id;
    private Supplier supplier;
    private Date date;

    public EntryNote(Long id, Supplier supplier, Date date) {
        this.id = id;
        this.supplier = supplier;
        this.date = date;
    }

    public EntryNote(Supplier supplier, Date date) {
        this.supplier = supplier;
        this.date = date;
    }

    public EntryNote(Supplier supplier) {
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
