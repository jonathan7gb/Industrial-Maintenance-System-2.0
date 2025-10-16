package org.maintenancesystem2.view;

import org.maintenancesystem2.model.Material;
import org.maintenancesystem2.view.helpers.InputHelper;
import org.maintenancesystem2.view.helpers.MessageHelper;

import java.util.List;
import java.util.Scanner;

public class MaterialView {

    Scanner sc = new Scanner(System.in);

    public Long inputMaterialId(){
        return (long) InputHelper.inputInteger("|| Insira o ID do Material: ", sc);
    }

    public String inputMaterialName(){
        String name;
        while(true){
            name = InputHelper.inputString("|| Insira o Nome do Material: ", sc);
            if(name.isEmpty()){
                MessageHelper.error("O nome não pode ser vazio!");
            }else{
                return name;
            }
        }
    }

    public String inputMaterialUnit(){
        String unit;
        while(true){
            unit = InputHelper.inputString("|| Insira a unidade de medida do Material: ", sc);
            if(unit.isEmpty()){
                MessageHelper.error("A medida não pode ser vazio!");
            }else{
                return unit;
            }
        }
    }


    public double inputMaterialQuantityInStock(){
        double quantity;
        while(true){
            quantity = InputHelper.inputDouble("|| Insira a quantidade desse Material: ", sc);
            if(quantity < 0){
                MessageHelper.error("Quantidade inválida!");
            }else{
                return quantity;
            }
        }
    }

    public void materialList(List<Material> materialList){
        if(materialList.isEmpty()){
            MessageHelper.error("Nenhum material foi encontrado!");
        }else{
            System.out.println("|| ------- MATERIAIS ------- ||");
            for(Material material : materialList){
                System.out.printf("|| [%d] %-12s |", material.getId(), material.getName());
                System.out.printf(" %.1f %s\n", material.getQuantityInStock(), material.getUnit());
                System.out.println("|| --------------------------------------------------------");
            }
        }
    }
}
