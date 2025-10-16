package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.EntryNote;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EntryNoteDAO {

    public void registerEntryNoteDAO(EntryNote en) throws SQLException {
        String command = "INSERT INTO NotaEntrada(idFornecedor) VALUES (?)";

        try(Connection conn = ConnectionDatabase.connect(); PreparedStatement stmt = conn.prepareStatement(command)) {
            stmt.setLong(1, en.getSupplier().getId());
            stmt.executeUpdate();
        }
    }
}
