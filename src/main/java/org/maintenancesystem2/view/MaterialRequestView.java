package org.maintenancesystem2.view;

import org.maintenancesystem2.view.helpers.InputHelper;
import org.maintenancesystem2.view.helpers.MessageHelper;

import java.util.Scanner;

public class MaterialRequestView {

    Scanner sc = new Scanner(System.in);

    public String inputSector(){
        String sector;
        while(true){
            sector = InputHelper.inputString("|| Insira o Setor: ", sc);
            if(sector.isEmpty()){
                MessageHelper.error("O setor não pode ser vazio!");
            }else{
                return sector;
            }
        }
    }

}
