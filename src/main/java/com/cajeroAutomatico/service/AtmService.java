package com.cajeroAutomatico.service;

import com.cajeroAutomatico.data.*;

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

    public static void sacarDinero(CajeroAutomatico cajero) {
        int amount = ClientInput.askAmount();
        String nif = ClientInput.askNif(cajero);
        Integer pin = ClientInput.askPin(cajero, nif);

        Tarjeta tarjeta = null;

        for (int i = 0; i < cajero.getTarjetas().size(); i++) {
            if (nif.equals(cajero.getTarjetas().get(i).getClientNif()) && pin.equals(cajero.getTarjetas().get(i).getClientPin()))
                tarjeta = cajero.getTarjetas().get(i);
        }

        if (tarjeta instanceof TarjetaDebito) {
            sacarDebitoArray(cajero, tarjeta, amount);
            // sacarDineroDebito(cajero, tarjeta, amount);
        } else if (tarjeta instanceof TarjetaCredito) {
            sacarCreditArray(cajero, tarjeta, amount);
            // sacarDineroCredit(cajero, tarjeta, amount);
        }
    }

    public static void sacarDineroDebito(CajeroAutomatico cajero, Tarjeta tarjeta, Integer dineroASacar) {
        int dineroSacado = 0;
        int cantidadBillete;
        int valorBillete;

        TarjetaDebito tarjetaDebito = (TarjetaDebito) tarjeta;

        int saldoTarjeta = tarjetaDebito.getSaldoDisponible();
        int saldoCajero = calcularSaldoCajero(cajero);

        if (saldoTarjeta < dineroASacar || saldoCajero < dineroASacar) {
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
            tarjetaDebito.setSaldoDisponible(tarjetaDebito.getSaldoDisponible() - dineroSacado);
            tarjetaDebito.mostrarTarjeta();
            mostrarCajero(cajero);
        }
    }

    public static void sacarDineroCredit(CajeroAutomatico cajero, Tarjeta tarjeta, Integer dineroASacar) {
        int saldoSacado = 0;
        int creditSacado = 0;
        int dineroSacado = 0;
        int cantidadBillete;
        int valorBillete;

        TarjetaCredito tarjetaCredit = (TarjetaCredito) tarjeta;

        int saldoTarjeta = tarjetaCredit.getSaldoDisponible();
        int creditTarjeta = tarjetaCredit.getCreditoDisponible();
        int saldoTotalTarjeta = saldoTarjeta + creditTarjeta;
        int saldoCajero = calcularSaldoCajero(cajero);

        if (saldoTotalTarjeta < dineroASacar || saldoCajero < dineroASacar) {
            System.out.println("Saldo no disponible.");
        } else {
            while (dineroASacar >= dineroSacado) {
                for (int i = 0; i < cajero.getBillsList().size(); i++) {
                    cantidadBillete = cajero.getBillsList().get(i).getQuantity();
                    valorBillete = cajero.getBillsList().get(i).getValue();
                    while (cantidadBillete > 0 && dineroASacar >= valorBillete) {
                        if (saldoTarjeta > 0) {
                            saldoSacado += valorBillete;
                            dineroASacar -= valorBillete;
                            saldoTarjeta -= valorBillete;
                            cantidadBillete--;
                            tarjetaCredit.setSaldoDisponible(saldoTarjeta);
                            cajero.getBillsList().get(i).setQuantity(cantidadBillete);
                        } else if (creditTarjeta > 0 && saldoTarjeta == 0) {
                            creditSacado += valorBillete;
                            dineroASacar -= valorBillete;
                            creditTarjeta -= valorBillete;
                            cantidadBillete--;
                            tarjetaCredit.setCreditoDisponible(creditTarjeta);
                            cajero.getBillsList().get(i).setQuantity(cantidadBillete);
                        }
                    }
                }
                dineroSacado = saldoSacado + creditSacado;
            }
            tarjetaCredit.mostrarTarjeta();
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

    public static void sacarDebitoArray(CajeroAutomatico cajero, Tarjeta tarjeta, Integer dineroASacar) {
        int dineroSacado = 0;
        int valorBillete;
        int cantidadBillete;

        TarjetaDebito tarjetaDebito = (TarjetaDebito) tarjeta;
        int saldoTarjeta = tarjetaDebito.getSaldoDisponible();
        int saldoCajero = saldoCajeroArray(cajero);

        if (dineroASacar > saldoCajero || dineroASacar > saldoTarjeta) {
            System.out.println("Saldo no disponible.");
        } else {
            while (dineroASacar >= dineroSacado) {
                for (int i = 0; i < cajero.getBilletes().length; i++) {
                    valorBillete = cajero.getBilletes()[i][0];
                    cantidadBillete = cajero.getBilletes()[i][1];
                    while (cantidadBillete > 0 && dineroASacar >= valorBillete) {
                        dineroSacado += valorBillete;
                        dineroASacar -= valorBillete;
                        cantidadBillete--;
                        cajero.getBilletes()[i][1]--;
                    }
                }
            }
            tarjetaDebito.setSaldoDisponible(saldoTarjeta - dineroSacado);
            tarjetaDebito.mostrarTarjeta();
            mostrarCajeroArray(cajero);
        }
    }

    public static void sacarCreditArray(CajeroAutomatico cajero, Tarjeta tarjeta, Integer dineroASacar) {
        int saldoSacado = 0;
        int creditSacado = 0;
        int dineroTotalSacado = 0;
        int valorBillete;
        int cantidadBillete;

        TarjetaCredito tarjetaCredit = (TarjetaCredito) tarjeta;

        int saldoTarjeta = tarjetaCredit.getSaldoDisponible();
        int creditTarjeta = tarjetaCredit.getCreditoDisponible();
        int saldoTotalTarjeta = saldoTarjeta + creditTarjeta;
        int saldoCajero = saldoCajeroArray(cajero);

        if (dineroASacar > saldoCajero || dineroASacar > saldoTotalTarjeta) {
            System.out.println("Saldo no disponible.");
        } else {
            while (dineroASacar >= dineroTotalSacado) {
                for (int i = 0; i < cajero.getBilletes().length; i++) {
                    valorBillete = cajero.getBilletes()[i][0];
                    cantidadBillete = cajero.getBilletes()[i][1];
                    while (cantidadBillete > 0 && dineroASacar >= valorBillete) {
                        if (saldoTarjeta > 0) {
                            saldoSacado += valorBillete;
                            dineroASacar -= valorBillete;
                            saldoTarjeta -= valorBillete;
                            cantidadBillete--;
                            cajero.getBilletes()[i][1]--;
                        } else if (creditTarjeta > 0 && saldoTarjeta == 0) {
                            creditSacado += valorBillete;
                            dineroASacar -= valorBillete;
                            creditTarjeta -= valorBillete;
                            cantidadBillete--;
                            cajero.getBilletes()[i][1]--;
                        }
                    }
                }
                dineroTotalSacado = saldoSacado + creditSacado;
            }
            tarjetaCredit.setSaldoDisponible(saldoTarjeta);
            tarjetaCredit.setCreditoDisponible(creditTarjeta);
            tarjetaCredit.mostrarTarjeta();
            mostrarCajeroArray(cajero);
        }
    }

    public static Integer saldoCajeroArray(CajeroAutomatico cajero) {
        int saldo = 0;
        for (int i = 0; i < cajero.getBilletes().length; i++) {
            saldo += cajero.getBilletes()[i][1] * cajero.getBilletes()[i][0];
        }
        return saldo;
    }

    public static void mostrarCajeroArray(CajeroAutomatico cajero) {
        int cantidadBillete;
        int valorBillete;
        for (int i = 0; i < cajero.getBilletes().length; i++) {
            cantidadBillete = cajero.getBilletes()[i][1];
            valorBillete = cajero.getBilletes()[i][0];
            System.out.println(cantidadBillete + " billetes de " + valorBillete + " €");
        }
    }
}