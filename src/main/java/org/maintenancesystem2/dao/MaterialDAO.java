package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialDAO {

    public void registerMaterialDAO(Material material) throws SQLException {
        String command = "INSERT INTO Material(nome, unidade, estoque) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setString(1, material.getName());
            stmt.setString(2, material.getUnit());
            stmt.setDouble(3, material.getQuantityInStock());
            stmt.executeUpdate();
        }
    }

    public boolean verifyIfNameAlreadyExists(String name) throws SQLException{
        String command = "SELECT nome FROM Material WHERE nome = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            }

            return false;
        }
    }
}
