package org.maintenancesystem2.controller;

import org.maintenancesystem2.view.helpers.MessageHelper;
import org.maintenancesystem2.view.menus.MainMenuView;

public class MainController {

    MainMenuView mainMenuView = new MainMenuView();

    public void mainController(){
        int opcao;
        while(true){
            opcao = mainMenuView.mainMenuView();

            switch (opcao){
//                case 1 -> ;
//                case 2 -> ;
//                case 3 -> ;
//                case 4 -> ;
//                case 5 -> ;
//                case 6 -> ;
                case 0 -> {return;}
                default -> MessageHelper.invalidIntInput();
            }
        }

    }
}
