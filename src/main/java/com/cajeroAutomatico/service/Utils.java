package com.cajeroAutomatico.service;

import com.cajeroAutomatico.data.CajeroAutomatico;

public class Utils {

    public static boolean checkOperation(String input) {
        return input.toLowerCase().trim().equals("salir") || input.toLowerCase().trim().equals("sacar dinero");
    }

    public static boolean checkNif(CajeroAutomatico cajero, String nif) {
        boolean flag = false;
        for (int i = 0; i < cajero.getTarjetas().size(); i++) {
            if (cajero.getTarjetas().get(i).getClientNif().equals(nif)) {
                flag = true;
                break;
            }
        }
        return !flag;
    }

    public static boolean checkPin(CajeroAutomatico cajero, Integer pin) {
        boolean flag = false;
        for (int i = 0; i < cajero.getTarjetas().size(); i++) {
            if (cajero.getTarjetas().get(i).getClientPin().equals(pin)) {
                flag = true;
                break;
            }
        }
        return !flag;
    }

    public static Boolean isInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
