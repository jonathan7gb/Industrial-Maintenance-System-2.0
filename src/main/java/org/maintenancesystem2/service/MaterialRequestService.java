package org.maintenancesystem2.service;

import org.maintenancesystem2.dao.MaterialDAO;
import org.maintenancesystem2.dao.MaterialRequestDAO;
import org.maintenancesystem2.model.Material;
import org.maintenancesystem2.view.MaterialRequestView;
import org.maintenancesystem2.view.MaterialView;
import org.maintenancesystem2.view.helpers.MessageHelper;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.List;

public class MaterialRequestService {

    MaterialView matView = new MaterialView();
    MaterialRequestView matReqView = new MaterialRequestView();
    MaterialDAO matDAO = new MaterialDAO();
    MaterialRequestDAO mrDAO = new MaterialRequestDAO();

    public void registerMaterialRequest(){
        Long supId;
        Long matId;
        try{
            System.out.println("\n|| ----- REGISTRAR REQUISIÇÃO DE MATERIAL ----- ||\n");
            String sector = matReqView.inputSector();
            if(sector.isBlank()){
                return;
            }else{
                    Long idMR = mrDAO.registerMaterialRequest(sector);
                    System.out.println();
                    List<Material> materials = matDAO.getAllMaterials();
                    while(true){
                        if(materials.isEmpty()){
                            MessageHelper.error("Nenhum Material Disponível!\n");
                            return;
                        }else{
                            matView.materialList(materials);
                            MessageHelper.info("Digite 0 para encerrar o loop de inserção de materiais!\n");

                            matId = matView.inputMaterialId();

                            if(matId == 0){
                                MessageHelper.info("Finalizando Registro de Nota de Entrada\n");
                                return;
                            }else{
                                Material material = matDAO.verifyIfIDExists(matId);
                                if (material == null || !materials.contains(material)) {
                                    MessageHelper.error("Material não encontrado com esse ID!\n");
                                }else{
                                    double quantity = matView.inputMaterialQuantityInStock();
                                    if(quantity > material.getQuantityInStock()){
                                        MessageHelper.error("Quantidade insuficiente!\n");
                                    }else{
                                        mrDAO.registerMaterialRequestItems(idMR, material, quantity);
                                        materials.remove(material);
                                    }
                                }
                            }

                    }
                }

            }
        }catch (SQLException e){
            MessageHelper.error(e.getMessage());
        }
    }

    public void acceptMaterialRequest(){
    try{
        System.out.println("\n|| ----- ACEITAR REQUISIÇÃO DE MATERIAL ----- ||");
        List<String> materialRequests = mrDAO.getAllMaterialRequests();
        matReqView.MaterialRequestList(materialRequests);
        if(materialRequests.isEmpty()){
            return;
        }else{
            Long idMR = matReqView.inputID();
            String materialRequest = mrDAO.getMaterialRequestByID(idMR);
            if(materialRequest == null){
                MessageHelper.error("Requisição de Item não encontrada!\n");
            }else{
                System.out.println("\n|| --------- INFO DA REQUISIÇÃO --------- ||");
                System.out.println(materialRequest);
                System.out.println("|| ------------------------------\n|| Materiais ------------------- ");
                List<String> materialRequestItems = mrDAO.getAllMaterialRequestsItems(idMR);
                for(String item : materialRequestItems){
                    System.out.println(item);
                    System.out.println("|| ------------------------------");
                }
                System.out.println("");

            }
        }
    }catch (SQLException e){
        MessageHelper.error(e.getMessage());
    }
    }
}
