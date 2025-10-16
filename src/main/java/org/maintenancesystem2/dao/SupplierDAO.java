package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {

    public void registerSupplierDAO(Supplier sup) throws SQLException {
        String command = "INSERT INTO Fornecedor(nome, cnpj) VALUES ( ?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, sup.getName());
            stmt.setString(2, sup.getCnpj());
            stmt.executeUpdate();
        }
    }

    public boolean verifyIfCnpjAlreadyExists(String cnpj) throws SQLException{
        String command = "SELECT cnpj FROM Fornecedor WHERE cnpj = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, cnpj);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

            return false;
        }
    }

    public List<Supplier> getAllSuppliers() throws SQLException {
        String command = "SELECT id, nome FROM Fornecedor";
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                Long id = rs.getLong("id");
                String name =  rs.getString("nome");
                suppliers.add(new Supplier(id,name));
            }

            return suppliers;
        }
    }

    public Supplier verifyIfIDExists(Long id) throws SQLException{
        String command = "SELECT id, nome FROM Fornecedor WHERE id = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Supplier(rs.getLong("id"),rs.getString("nome"));
            }

            return null;
        }
    }
}
