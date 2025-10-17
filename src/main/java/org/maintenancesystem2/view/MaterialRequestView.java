package org.maintenancesystem2.view;

import org.maintenancesystem2.view.helpers.InputHelper;
import org.maintenancesystem2.view.helpers.MessageHelper;

import java.util.List;
import java.util.Scanner;

public class MaterialRequestView {

    Scanner sc = new Scanner(System.in);

    public Long inputID(){
        Long id;
        while(true){
            id = (long) InputHelper.inputInteger("|| Insira o ID da Requisição: ", sc);
            if(id == null){
                MessageHelper.error("O ID não pode ser vazio!");
            }else{
                return id;
            }
        }
    }

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

    public void MaterialRequestList(List<String> materialRequest){
        if(materialRequest.isEmpty()){
            MessageHelper.error("Nenhuma Requisição encontrada!\n");
        }else{
            for(String str : materialRequest){
                System.out.println(str);
                System.out.println("|| --------------------------");
            }
        }
    }
}
