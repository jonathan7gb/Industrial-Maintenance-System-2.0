package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Material> getAllMaterials() throws SQLException {
        String command = "SELECT id, nome, unidade, estoque FROM Material";
        List<Material> materials = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                Long id = rs.getLong("id");
                String name =  rs.getString("nome");
                String unidade = rs.getString("unidade");
                double estoque =  rs.getDouble("estoque");
                materials.add(new Material(id,name, unidade, estoque));
            }

            return materials;
        }
    }

    public Material verifyIfIDExists(Long id) throws SQLException{
        String command = "SELECT id, nome, unidade, estoque FROM Material WHERE id = ?";

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Material(rs.getLong("id"),rs.getString("nome"), rs.getString("unidade"), rs.getDouble("estoque"));
            }

            return null;
        }
    }


}
