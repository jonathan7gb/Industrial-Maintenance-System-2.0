package org.maintenancesystem2.service;

import org.maintenancesystem2.dao.MaterialDAO;
import org.maintenancesystem2.model.Material;
import org.maintenancesystem2.view.MaterialView;
import org.maintenancesystem2.view.helpers.MessageHelper;

import java.sql.SQLException;

public class MaterialService {

    MaterialView materialView = new MaterialView();
    MaterialDAO materialDao = new MaterialDAO();

    public void registerMaterial() {
        try{
            System.out.println("\n|| ------ Cadastrar Material ------ ||");
            String name = materialView.inputMaterialName();
            String unit = materialView.inputMaterialUnit();
            double quantity = materialView.inputMaterialQuantityInStock();
            boolean nameExists = materialDao.verifyIfNameAlreadyExists(name);
            if(nameExists){
                MessageHelper.error("Nome já em uso.\n");
            }else{
                Material material = new Material(name, unit, quantity);
                materialDao.registerMaterialDAO(material);
                MessageHelper.success("Material cadastrado com sucesso.\n");
            }
        }catch (SQLException e){
            MessageHelper.error(e.getMessage());
        }
    }
}
