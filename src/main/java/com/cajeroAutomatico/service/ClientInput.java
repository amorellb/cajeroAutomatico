package com.cajeroAutomatico.service;

import java.util.Scanner;

public class ClientInput {
    private static Scanner input = new Scanner(System.in);

    public static Integer amount() {
        System.out.println("Cuánto dinero quieres sacar?");
        return input.nextInt();
    }
}
