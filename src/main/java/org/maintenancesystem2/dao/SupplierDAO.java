package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAO {

    public void registerSupplierDAO(Supplier sup) throws SQLException {
        String command = "INSERT INTO suppliers(nome, cnpj) VALUES ( ?, ?)";

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
}
