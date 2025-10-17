package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.Material;

import java.sql.*;

public class MaterialRequestDAO {

    public Long registerMaterialRequest(String sector) throws SQLException {
        String command = "INSERT INTO Requisicao(setor) VALUES (?)";
        Long idMaterialRequest = null;
        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, sector);
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idMaterialRequest = rs.getLong(1);
                    }
                }
            }
        }
        return idMaterialRequest;
    }

    public void registerMaterialRequestItems(Long idMaterialRequest, Material material, double Quantity) throws SQLException {
        String command = "INSERT INTO RequisicaoItem(idRequisicao, idMaterial, quantidade) VALUES (?, ?, ?)";

        try {
            Connection conn = ConnectionDatabase.connect();

            try (PreparedStatement stmt = conn.prepareStatement(command)) {
                stmt.setLong(1, idMaterialRequest);
                stmt.setLong(2, material.getId());
                stmt.setDouble(3, Quantity);
                stmt.executeUpdate();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
