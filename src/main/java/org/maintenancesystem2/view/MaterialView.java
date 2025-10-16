package org.maintenancesystem2.view;

import org.maintenancesystem2.view.helpers.InputHelper;
import org.maintenancesystem2.view.helpers.MessageHelper;

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
            quantity = InputHelper.inputDouble("|| Insira a quantidade em estoque desse Material: ", sc);
            if(quantity < 0){
                MessageHelper.error("A quantidade não pode ser negativa");
            }else{
                return quantity;
            }
        }
    }
}
