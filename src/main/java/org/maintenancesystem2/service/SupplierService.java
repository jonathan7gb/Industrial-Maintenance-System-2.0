package org.maintenancesystem2.service;

import org.maintenancesystem2.dao.SupplierDAO;
import org.maintenancesystem2.model.Supplier;
import org.maintenancesystem2.view.SupplierView;
import org.maintenancesystem2.view.helpers.MessageHelper;

import java.sql.SQLException;

public class SupplierService {

    SupplierView supView = new SupplierView();
    SupplierDAO supDao = new SupplierDAO();

    public void registerSupplier() {
    try{
        System.out.println("\n|| ------ Cadastrar Fornecedor ------ ||");
        String name = supView.inputSupplierName();
        String cnpj = supView.inputSupplierCnpj();
        boolean cnpjExists = supDao.verifyIfCnpjAlreadyExists(cnpj);
        if(cnpjExists){
            MessageHelper.error("CNPJ já cadastrado.\n");
        }else{
            Supplier sup = new Supplier(name, cnpj);
            supDao.registerSupplierDAO(sup);
            MessageHelper.success("Fornecedor cadastrado com sucesso.\n");
        }
    }catch (SQLException e){
        MessageHelper.error(e.getMessage());
    }
    }
}
