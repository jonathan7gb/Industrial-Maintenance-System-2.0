package org.maintenancesystem2.controller;

import org.maintenancesystem2.model.EntryNote;
import org.maintenancesystem2.service.EntryNoteService;
import org.maintenancesystem2.service.MaterialRequestService;
import org.maintenancesystem2.service.MaterialService;
import org.maintenancesystem2.service.SupplierService;
import org.maintenancesystem2.view.helpers.MessageHelper;
import org.maintenancesystem2.view.menus.MainMenuView;

public class MainController {

    MainMenuView mainMenuView = new MainMenuView();
    SupplierService supService = new SupplierService();
    MaterialService matService = new MaterialService();
    EntryNoteService enService = new EntryNoteService();
    MaterialRequestService mrService = new MaterialRequestService();

    public void mainController(){
        int opcao;
        while(true){
            opcao = mainMenuView.mainMenuView();

            switch (opcao){
                case 1 -> supService.registerSupplier();
                case 2 -> matService.registerMaterial();
                case 3 -> enService.registerEntryNote();
                case 4 -> mrService.registerMaterialRequest();
                case 5 -> mrService.acceptMaterialRequest();
//                case 6 -> ;
                case 0 -> {
                    System.out.println("\n|| ----- SISTEMA ENCERRADO ----- ||");
                    return;
                }
                default -> MessageHelper.invalidIntInput();
            }
        }

    }
}
