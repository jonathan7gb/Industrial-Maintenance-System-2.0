package org.maintenancesystem2.model;

import java.util.Objects;

public class Material {

    private Long id;
    private String name;
    private String unit;
    private double quantityInStock;

    public Material(Long id, String name, String unit, double quantityInStock) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.quantityInStock = quantityInStock;
    }

    public Material(String name, String unit, double quantityInStock) {
        this.name = name;
        this.unit = unit;
        this.quantityInStock = quantityInStock;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantity(double quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity=" + quantityInStock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return Double.compare(quantityInStock, material.quantityInStock) == 0 && Objects.equals(id, material.id) && Objects.equals(name, material.name) && Objects.equals(unit, material.unit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unit, quantityInStock);
    }
}
