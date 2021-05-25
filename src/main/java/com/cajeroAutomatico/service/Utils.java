package com.cajeroAutomatico.service;

import com.cajeroAutomatico.data.CajeroAutomatico;

public class Utils {

    public static boolean checkOperation(String input) {
        return input.equals("salir") || input.equals("sacar dinero");
    }

    public static boolean checkNif(CajeroAutomatico cajero, String nif) {
        boolean flag = false;
        for (int i = 0; i < cajero.getTarjetas().size(); i++) {
            if (cajero.getTarjetas().get(i).getClientNif().equals(nif)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public static boolean checkPin(CajeroAutomatico cajero, String nif, Integer pin) {
        boolean flag = false;
        String clientNif;
        Integer pinCode;

        for (int i = 0; i < cajero.getTarjetas().size(); i++) {
            clientNif = cajero.getTarjetas().get(i).getClientNif();
            pinCode = cajero.getTarjetas().get(i).getClientPin();
            if (clientNif.equals(nif) && pinCode.equals(pin)) {
                flag = true;
            }
        }
        return flag;
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
