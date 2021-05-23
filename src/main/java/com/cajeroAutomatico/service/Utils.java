package com.cajeroAutomatico.service;

import com.cajeroAutomatico.data.CajeroAutomatico;
import com.cajeroAutomatico.data.Tarjeta;
import com.cajeroAutomatico.data.TarjetaDebito;

public class Utils {

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

    public static boolean checkPin(CajeroAutomatico cajero, Integer pin) {
        boolean flag = false;
        for (int i = 0; i < cajero.getTarjetas().size(); i++) {
            if (cajero.getTarjetas().get(i).getClientPin().equals(pin)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
