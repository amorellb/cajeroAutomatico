package com.cajeroAutomatico.service;

import com.cajeroAutomatico.data.Billetes;
import com.cajeroAutomatico.data.CajeroAutomatico;
import com.cajeroAutomatico.data.TarjetaCredito;
import com.cajeroAutomatico.data.TarjetaDebito;

public class AtmService {

    public static CajeroAutomatico inicializarCajero(CajeroAutomatico cajero) {
        CajeroAutomatico nuevoCajero = new CajeroAutomatico();
        nuevoCajero.setIdCajero(cajero.getIdCajero() + 1);
        nuevoCajero.setIdUltCajero(nuevoCajero.getIdCajero() - 1);
        nuevoCajero.getBillsList().add(new Billetes(500, 2));
        nuevoCajero.getBillsList().add(new Billetes(200, 5));
        nuevoCajero.getBillsList().add(new Billetes(100, 5));
        nuevoCajero.getBillsList().add(new Billetes(50, 5));
        nuevoCajero.getBillsList().add(new Billetes(20, 10));
        nuevoCajero.getBillsList().add(new Billetes(10, 10));
        nuevoCajero.getBillsList().add(new Billetes(5, 20));

        return nuevoCajero;
    }

    public static void mostrarCajero(CajeroAutomatico cajero) {
        for (int i = 0; i < cajero.getBillsList().size(); i++) {
            System.out.println(cajero.getBillsList().get(i).getQuantity() + " billetes de " + cajero.getBillsList().get(i).getValue() + " €");
        }
    }

    public void sacarDineroDebito(CajeroAutomatico cajero, String nif, Integer pin, TarjetaDebito tarjeta) {
        if (!Utils.checkNif(cajero, nif)) {
            System.out.println("NIF incorrecto");
        }
        if (!Utils.checkPin(cajero, pin)) {
            System.out.println("NIF incorrecto");
        }

        int dineroASacar = ClientInput.amount();
        int dineroSacado = 0;

        if (Utils.checkNif(cajero, nif) && Utils.checkPin(cajero, pin)) {
            if (tarjeta.getSaldoDisponible() < dineroASacar) {
                System.out.println("Saldo no disponible.");
            } else {
                while (dineroASacar >= dineroSacado) {
                    for (int i = 0; i < cajero.getBillsList().size(); i++) {
                        Integer cantidadBillete = cajero.getBillsList().get(0).getQuantity();
                        Integer valorBillete = cajero.getBillsList().get(0).getValue();
                        while (cantidadBillete > 0 && dineroASacar > valorBillete) {
                            dineroSacado += valorBillete;
                            dineroASacar -= valorBillete;
                            cantidadBillete--;
                        }
                    }
                }
                tarjeta.setSaldoDisponible(tarjeta.getSaldoDisponible() - dineroSacado);
            }
        }
    }

    public void sacarDineroCredito(CajeroAutomatico cajero, String nif, Integer pin, TarjetaCredito tarjeta) {
        if (!Utils.checkNif(cajero, nif)) {
            System.out.println("NIF incorrecto");
        }
        if (!Utils.checkPin(cajero, pin)) {
            System.out.println("NIF incorrecto");
        }

        int dineroASacar = ClientInput.amount();
        int saldoSacado = 0;
        int creditoSacado = 0;
        int dineroSacado = 0;

        if (Utils.checkNif(cajero, nif) && Utils.checkPin(cajero, pin)) {
            int saldo = tarjeta.getSaldoDisponible();
            int credito = tarjeta.getCreditoDisponible();
            int saldoTotal = saldo + credito;
            if (saldoTotal < dineroASacar) {
                System.out.println("Saldo no disponible.");
            } else {
                while (dineroASacar >= dineroSacado) {
                    for (int i = 0; i < cajero.getBillsList().size(); i++) {
                        Integer cantidadBillete = cajero.getBillsList().get(0).getQuantity();
                        Integer valorBillete = cajero.getBillsList().get(0).getValue();
                        while (cantidadBillete > 0 && dineroASacar > valorBillete) {
                            if (saldoSacado <= saldo) {
                                saldoSacado += valorBillete;
                                dineroASacar -= valorBillete;
                                cantidadBillete--;
                                saldo -= saldoSacado;
                                tarjeta.setSaldoDisponible(saldo);
                            } else if (creditoSacado <= credito) {
                                creditoSacado += valorBillete;
                                dineroASacar -= valorBillete;
                                cantidadBillete--;
                                credito -= creditoSacado;
                                tarjeta.setCreditoDisponible(credito);
                            }
                        }
                    }
                    dineroSacado = saldoSacado + creditoSacado;
                }
            }
        }
    }
}
