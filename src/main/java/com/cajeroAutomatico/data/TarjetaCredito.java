package com.cajeroAutomatico.data;

public class TarjetaCredito extends Tarjeta {
    private Integer saldoDisponible;
    private Integer creditoDisponible;

    public TarjetaCredito() {
        super();
    }

    public TarjetaCredito(String clientNif, Integer clientPin, String clientName, String clientSurname, Integer saldoDisponible, Integer creditoDisponible) {
        super(clientNif, clientPin, clientName, clientSurname);
        this.saldoDisponible = saldoDisponible;
        this.creditoDisponible = creditoDisponible;
    }

    public TarjetaCredito(TarjetaCredito tarjetaCredito) {
        super(tarjetaCredito.getClientNif(), tarjetaCredito.getClientPin(), tarjetaCredito.getClientName(), tarjetaCredito.getClientSurname());
        this.saldoDisponible = tarjetaCredito.saldoDisponible;
        this.creditoDisponible = tarjetaCredito.creditoDisponible;
    }

    @Override
    public void mostrarTarjeta() {
        super.mostrarTarjeta();
        System.out.println("Saldo: " + this.saldoDisponible);
        System.out.println("Crédito: " + this.creditoDisponible);
    }

    public Integer getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(Integer saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public Integer getCreditoDisponible() {
        return creditoDisponible;
    }

    public void setCreditoDisponible(Integer creditoDisponible) {
        this.creditoDisponible = creditoDisponible;
    }
}
