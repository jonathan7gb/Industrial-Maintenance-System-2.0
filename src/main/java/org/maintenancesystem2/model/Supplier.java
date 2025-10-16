package org.maintenancesystem2.model;

public class Supplier {

    private Long id;
    private String name;
    private String cnpj;

    public Supplier(Long id, String name, String cnpj) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
    }

    public Supplier(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Supplier(String name, String cnpj) {
        this.name = name;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnpj='" + cnpj + '\'' +
                '}';
    }
}
