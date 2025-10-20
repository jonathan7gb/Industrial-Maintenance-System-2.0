package org.maintenancesystem2.dao;

import org.maintenancesystem2.connectionDatabase.ConnectionDatabase;
import org.maintenancesystem2.model.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<String> getAllMaterialRequests() throws SQLException {
        String command = """
                SELECT id, setor, dataSolicitacao
                FROM Requisicao 
                WHERE status = 'PENDENTE'
                """;

        List<String> materialRequests = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command);
             ResultSet rs = stmt.executeQuery()) {

            while(rs.next()) {
                Long id = rs.getLong("id");
                String sector =  rs.getString("setor");
                Date date = rs.getDate("dataSolicitacao");
                materialRequests.add("|| ["+id+"] " + date.toString() + "\n|| Setor: "+ sector);
            }

            return materialRequests;
        }
    }

    public List<String> getAllMaterialRequestsItems(Long idRequisicao) throws SQLException {
        String command = """
                SELECT M.nome as materialNome, M.unidade as unidade, RI.quantidade as quantidade
                FROM RequisicaoItem as RI 
                JOIN Requisicao as R on R.id = RI.idRequisicao
                JOIN Material as M on M.id = RI.idMaterial
                WHERE R.id = ?
                """;

        List<String> materialRequestsItems = new ArrayList<>();

        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, idRequisicao);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {

                String materialName = rs.getString("materialNome");
                String materialUnit = rs.getString("unidade");
                double materialQuantity = rs.getDouble("quantidade");


                materialRequestsItems.add("|| " + materialQuantity+ " "+ materialUnit+" - " + materialName);
            }

            return materialRequestsItems;
        }

    }

    public String getMaterialRequestByID(Long idRequisicao) throws SQLException {
        String command = """
                SELECT id, setor, dataSolicitacao
                FROM Requisicao 
                WHERE status = 'PENDENTE'
                AND id = ?
                """;


        try (Connection conn = ConnectionDatabase.connect();
             PreparedStatement stmt = conn.prepareStatement(command)) {

            stmt.setLong(1, idRequisicao);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Long id = rs.getLong("id");
                String sector = rs.getString("setor");
                Date date = rs.getDate("dataSolicitacao");

                return "|| [" + id + "] " + date.toString() + "\n|| Setor: " + sector;
            }

            return null;
        }
    }
}
