package org.maintenancesystem2.service;

import org.maintenancesystem2.dao.EntryNoteDAO;
import org.maintenancesystem2.dao.MaterialDAO;
import org.maintenancesystem2.dao.SupplierDAO;
import org.maintenancesystem2.model.EntryNote;
import org.maintenancesystem2.model.Material;
import org.maintenancesystem2.model.Supplier;
import org.maintenancesystem2.view.MaterialView;
import org.maintenancesystem2.view.SupplierView;
import org.maintenancesystem2.view.helpers.MessageHelper;

import java.util.List;
import java.awt.*;
import java.sql.SQLException;

public class EntryNoteService {

    SupplierView supView = new SupplierView();
    SupplierDAO supDAO = new SupplierDAO();
    MaterialView matView = new MaterialView();
    MaterialDAO matDAO = new MaterialDAO();
    EntryNoteDAO enDAO = new EntryNoteDAO();

    public void registerEntryNote(){
        Long supId;
        Long matId;
        try{
            System.out.println("\n|| ----- REGISTRAR NOTA DE ENTRADA ----- ||\n");
            List<Supplier> supList = supDAO.getAllSuppliers();
            supView.supplierList(supList);
            if(supList.isEmpty()){
                return;
            }else{
                supId = supView.inputSupplierId();
                Supplier supplier = supDAO.verifyIfIDExists(supId);

                if (supplier == null) {
                    MessageHelper.error("Fornecedor não encontrado com esse ID!\n");
                }else{
                    System.out.println();
                    EntryNote entryNote = new EntryNote(supplier);
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
                                return;
                            }else{
                                Material material = matDAO.verifyIfIDExists(matId);
                                if (material == null || !materials.contains(material)) {
                                    MessageHelper.error("Material não encontrado com esse ID!\n");
                                }else{
                                    while(true) {
                                        double quantity = matView.inputMaterialQuantityInStock();
                                        if (quantity > material.getQuantityInStock()) {
                                            MessageHelper.error("Quantidade de materiais insuficiente!\n");
                                        } else {
                                            break;
                                        }
                                        materials.remove(material);
                                    }
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
}
