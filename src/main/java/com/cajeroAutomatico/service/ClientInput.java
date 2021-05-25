package com.cajeroAutomatico.service;

import com.cajeroAutomatico.data.CajeroAutomatico;

import java.util.Scanner;

public class ClientInput {
    private static final Scanner input = new Scanner(System.in);

    public static String askOperation() {
        System.out.println(" ");
        System.out.println("Qué operación deseas realizar?");
        String operation;
        do {
            operation = input.nextLine();
            if (!Utils.checkOperation(operation)) {
                System.out.println("Operación no válida");
            }
        } while (!Utils.checkOperation(operation));
        return operation;
    }

    public static Integer askAmount() {
        System.out.println("Cuánto dinero quieres sacar?");
        String amount;
        int cantidad;
        do {
            amount = input.nextLine();
            while (!Utils.isInt(amount)) {
                System.out.println("Input no válido");
                System.out.println("Cuánto dinero quieres sacar?");
                amount = input.nextLine();
            }
            cantidad = Integer.parseInt(amount);
            if (cantidad % 5 != 0) {
                System.out.println("La cantidad de dinero a sacar debe ser múltiplo de 5");
                System.out.println("Cuánto dinero quieres sacar?");
            }
        } while (cantidad % 5 != 0);
        return cantidad;
    }

    public static String askNif(CajeroAutomatico cajero) {
        System.out.println("Introduce el NIF");
        String nif;
        do {
            nif = input.nextLine();
            if (Utils.checkNif(cajero, nif)) {
                System.out.println("NIF incorrecto");
                System.out.println("Introduce el NIF");
            }
        } while (Utils.checkNif(cajero, nif));
        return nif;
    }

    public static Integer askPin(CajeroAutomatico cajero) {
        System.out.println("Introduce el PIN");
        String pinCode;
        int pin;
        do {
            pinCode = input.nextLine();
            while (!Utils.isInt(pinCode)) {
                System.out.println("Input no válido");
                System.out.println("Introduce el PIN");
                pinCode = input.nextLine();
            }
            pin = Integer.parseInt(pinCode);
            if (Utils.checkPin(cajero, pin)) {
                System.out.println("PIN incorrecto");
                System.out.println("Introduce el PIN");
            }
        } while (Utils.checkPin(cajero, pin));
        return pin;
    }
}
