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
        nuevoCajero.getBillsList().add(new Billetes(500, 10));
        nuevoCajero.getBillsList().add(new Billetes(200, 20));
        nuevoCajero.getBillsList().add(new Billetes(100, 40));
        nuevoCajero.getBillsList().add(new Billetes(50, 80));
        nuevoCajero.getBillsList().add(new Billetes(20, 100));
        nuevoCajero.getBillsList().add(new Billetes(10, 100));
        nuevoCajero.getBillsList().add(new Billetes(5, 500));

        return nuevoCajero;
    }

    public static void mostrarCajero(CajeroAutomatico cajero) {
        for (int i = 0; i < cajero.getBillsList().size(); i++) {
            System.out.println(cajero.getBillsList().get(i).getQuantity() + " billetes de " + cajero.getBillsList().get(i).getValue() + " €");
        }
    }

    public static void sacarDineroDebito(CajeroAutomatico cajero, TarjetaDebito tarjeta, Integer amount) {
        int dineroASacar = amount;
        int dineroSacado = 0;
        int cantidadBillete;
        int valorBillete;

        int saldo = tarjeta.getSaldoDisponible();
        int saldoCajero = calcularSaldoCajero(cajero);

        if (saldo < dineroASacar || saldoCajero < dineroASacar) {
            System.out.println("Saldo no disponible.");
        } else {
            while (dineroASacar >= dineroSacado) {
                for (int i = 0; i < cajero.getBillsList().size(); i++) {
                    cantidadBillete = cajero.getBillsList().get(i).getQuantity();
                    valorBillete = cajero.getBillsList().get(i).getValue();
                    while (cantidadBillete > 0 && dineroASacar >= valorBillete) {
                        dineroSacado += valorBillete;
                        dineroASacar -= valorBillete;
                        cantidadBillete--;
                        cajero.getBillsList().get(i).setQuantity(cantidadBillete);
                    }
                }
            }
            tarjeta.setSaldoDisponible(tarjeta.getSaldoDisponible() - dineroSacado);
            tarjeta.mostrarTarjeta();
            mostrarCajero(cajero);
        }

    }

    public static void sacarDineroCredito(CajeroAutomatico cajero, TarjetaCredito tarjeta, Integer amount) {
        int dineroASacar = amount;
        int saldoSacado = 0;
        int creditoSacado = 0;
        int dineroSacado = 0;
        int cantidadBillete;
        int valorBillete;

        int saldo = tarjeta.getSaldoDisponible();
        int credito = tarjeta.getCreditoDisponible();
        int saldoTotal = saldo + credito;
        int saldoCajero = calcularSaldoCajero(cajero);

        if (saldoTotal < dineroASacar || saldoCajero < dineroASacar) {
            System.out.println("Saldo no disponible.");
        } else {
            while (dineroASacar >= dineroSacado) {
                for (int i = 0; i < cajero.getBillsList().size(); i++) {
                    cantidadBillete = cajero.getBillsList().get(i).getQuantity();
                    valorBillete = cajero.getBillsList().get(i).getValue();
                    while (cantidadBillete > 0 && dineroASacar > valorBillete) {
                        if (saldoSacado <= saldo) {
                            saldoSacado += valorBillete;
                            dineroASacar -= valorBillete;
                            cantidadBillete--;
                            saldo -= saldoSacado;
                            tarjeta.setSaldoDisponible(saldo);
                            cajero.getBillsList().get(i).setQuantity(cantidadBillete);
                        } else if (creditoSacado <= credito) {
                            creditoSacado += valorBillete;
                            dineroASacar -= valorBillete;
                            cantidadBillete--;
                            credito -= creditoSacado;
                            tarjeta.setCreditoDisponible(credito);
                            cajero.getBillsList().get(i).setQuantity(cantidadBillete);
                        }
                    }
                }
                dineroSacado = saldoSacado + creditoSacado;
            }
            tarjeta.mostrarTarjeta();
            mostrarCajero(cajero);
        }
    }

    private static Integer calcularSaldoCajero(CajeroAutomatico cajero) {
        int saldoCajero = 0;
        int cantidadBillete;
        int valorBillete;
        for (int i = 0; i < cajero.getBillsList().size(); i++) {
            cantidadBillete = cajero.getBillsList().get(i).getQuantity();
            valorBillete = cajero.getBillsList().get(i).getValue();
            saldoCajero += cantidadBillete * valorBillete;
        }
        return saldoCajero;
    }
}
