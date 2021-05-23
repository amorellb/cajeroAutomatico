package com.cajeroAutomatico.data;

public abstract class Tarjeta implements CardInfo {
    private String clientNif;
    private Integer clientPin;
    private String clientName;
    private String clientSurname;
    private Boolean isActivated = true;

    public Tarjeta() {
    }

    public Tarjeta(String clientNif, Integer clientPin, String clientName, String clientSurname) {
        this.clientNif = clientNif;
        this.clientPin = clientPin;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
    }

    public Tarjeta(Tarjeta tarjeta) {
        this.clientNif = tarjeta.clientNif;
        this.clientPin = tarjeta.clientPin;
        this.clientName = tarjeta.clientName;
        this.clientSurname = tarjeta.clientSurname;
    }

    @Override
    public void mostrarTarjeta() {
        System.out.println("NIF: " + this.clientNif);
        System.out.println("Nombre: " + this.clientName);
        System.out.println("Apellido: " + this.clientSurname);
    }

    public String getClientNif() {
        return clientNif;
    }

    public void setClientNif(String clientNif) {
        this.clientNif = clientNif;
    }

    public Integer getClientPin() {
        return clientPin;
    }

    public void setClientPin(Integer clientPin) {
        this.clientPin = clientPin;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public Boolean getActivated() {
        return isActivated;
    }

    public void setActivated(Boolean activated) {
        isActivated = activated;
    }
}
