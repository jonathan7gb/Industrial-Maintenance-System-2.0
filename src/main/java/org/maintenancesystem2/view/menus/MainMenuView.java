package org.maintenancesystem2.view.menus;

import org.maintenancesystem2.view.helpers.InputHelper;

import java.util.Scanner;

public class MainMenuView {

    public int mainMenuView(){
        System.out.println("""
                || ---- SISTEMA DE GESTÃO DE ALMOXARIFADO INDUSTRIAL ---- ||
                || [1] Cadastrar Fornecedor
                || [2] Cadastrar Material
                || [3] Registrar Nota de Entrada
                || [4] Criar Requisição de Material
                || [5] Atender Requisição
                || ------------------------------------------------------
                || [0] Sair
                || ------------------------------------------------------""");
                return InputHelper.inputInteger("|| Sua Escolha: ", new Scanner(System.in));
    }
}
