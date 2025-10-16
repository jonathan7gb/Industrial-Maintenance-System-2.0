package org.maintenancesystem2.view;

import org.maintenancesystem2.model.Supplier;
import org.maintenancesystem2.view.helpers.InputHelper;
import org.maintenancesystem2.view.helpers.MessageHelper;
import java.util.List;
import java.util.ArrayList;

import java.awt.*;
import java.util.Scanner;

public class SupplierView {

    Scanner sc = new Scanner(System.in);

    public Long inputSupplierId(){
        return (long) InputHelper.inputInteger("|| Insira o ID do Fornecedor: ", sc);
    }

    public String inputSupplierName(){
        String name;
        while(true){
            name = InputHelper.inputString("|| Insira o Nome do Fornecedor: ", sc);
            if(name.isEmpty()){
                MessageHelper.error("O nome não pode ser vazio!");
            }else{
                return name;
            }
        }
    }

    public String inputSupplierCnpj(){
        while(true){
            String cnpj = InputHelper.inputString("|| Insira o CNPJ do Fornecedor: ", sc);
            if(cnpj.isEmpty()){
                MessageHelper.error("O CNPJ não pode ser vazio!\n");
            } else if (cnpj.length() != 14) {
                MessageHelper.error("O CNPJ deve possuir 14 números!\n");
            }else if(!cnpj.matches("\\d+")){
                MessageHelper.error("O CNPJ deve possui apenas números!\n");
            }else{
                return cnpj;
            }
        }
    }

    public void supplierList(List<Supplier> supplierList){
        if(supplierList.isEmpty()){
            MessageHelper.error("Nenhum fornecedor foi encontrado!");
        }else{
            System.out.println("|| ------ FORNECEDORES ------ ||");
            for(Supplier supplier : supplierList){
                System.out.printf("|| [%d] %s\n", supplier.getId(), supplier.getName());
                System.out.println("|| --------------------------------------------------------");
            }
        }
    }
}
