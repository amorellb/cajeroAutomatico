package com.cajeroAutomatico.data;

public class TarjetaDebito extends Tarjeta{
    private Integer saldoDisponible;

    public TarjetaDebito() {
    }

    public TarjetaDebito(String clientNif, Integer clientPin, String clientName, String clientSurname, Integer saldoDisponible) {
        super(clientNif, clientPin, clientName, clientSurname);
        this.saldoDisponible = saldoDisponible;
    }

    public TarjetaDebito(TarjetaDebito tarjetaDebito) {
        super(tarjetaDebito.getClientNif(), tarjetaDebito.getClientPin(), tarjetaDebito.getClientName(), tarjetaDebito.getClientSurname());
        this.saldoDisponible = tarjetaDebito.saldoDisponible;
    }

    @Override
    public void mostrarTarjeta() {
        super.mostrarTarjeta();
        System.out.println("Saldo: " + this.saldoDisponible);
    }

    public Integer getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Integer saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
}
