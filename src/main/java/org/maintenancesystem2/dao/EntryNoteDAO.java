package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.EntryNote;
import org.maintenancesystem2.model.Material;

import java.sql.*;

public class EntryNoteDAO {

    public Long registerEntryNoteDAO(EntryNote en) throws SQLException {
        String command = "INSERT INTO NotaEntrada(idFornecedor) VALUES (?)";
        Long idEntryNote = null;
        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, en.getSupplier().getId());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        idEntryNote = rs.getLong(1);
                    }
                }
            }
        }
        return idEntryNote;
    }

    public void registerEntryNoteItems(Long idEntry, Material material, double Quantity) throws SQLException {
        String command = "INSERT INTO NotaEntradaItem(idNotaEntrada, idMaterial, quantidade) VALUES (?, ?, ?)";

        String updateMaterialQuantityInStock = "UPDATE Material SET estoque = estoque + ? WHERE id = ?";
        try {
            Connection conn = ConnectionDatabase.connect();

            try (PreparedStatement stmt = conn.prepareStatement(command)) {
                stmt.setLong(1, idEntry);
                stmt.setLong(2, material.getId());
                stmt.setDouble(3, Quantity);
                stmt.executeUpdate();
            }

            try (PreparedStatement stmt = conn.prepareStatement(updateMaterialQuantityInStock)) {
                stmt.setDouble(1, Quantity);
                stmt.setLong(2, material.getId());
                stmt.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
