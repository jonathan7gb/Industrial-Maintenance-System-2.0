package org.maintenancesystem2.view.helpers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {
    public static int inputInteger(String mensagem, Scanner sc) {
        int leitura = -1;
        do {
            System.out.print(mensagem);
            String leituraStr = sc.nextLine();
            try {
                leitura = Integer.parseInt(leituraStr);
                break;
            } catch (NumberFormatException e) {
                MessageHelper.invalidIntInput();
                continue;
            }
        } while (true);
        return leitura;
    }

    //=============================================================================

    public static double inputDouble(String mensagem, Scanner sc) {

        double leitura = -1;
        do {
            System.out.print(mensagem);
            String leituraStr = sc.nextLine();
            try {
                leitura = Double.parseDouble(leituraStr);
                break;
            } catch (NumberFormatException e) {
                MessageHelper.invalidDoubleInput();
                continue;
            }
        } while (true);
        return leitura;
    }

    //=============================================================================

    public static String inputString(String mensagem, Scanner sc) {
        System.out.print(mensagem);
        return sc.nextLine();
    }

    //=============================================================================

    public static LocalDate inputDate(String mensagem, Scanner sc) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (true) {
            System.out.print(mensagem);
            String input = sc.nextLine();

            try {
                LocalDate data = LocalDate.parse(input, formatter);

                int ano = data.getYear();
                if (ano < 1900 || ano > 2026) {
                    MessageHelper.error("Ano inválido. Deve estar entre 1900 e 2026.");
                    continue;
                }

                return data;

            } catch (DateTimeParseException e) {
                MessageHelper.error("Data inválida. Use o formato dd/MM/yyyy.");
            }
        }
    }
}
