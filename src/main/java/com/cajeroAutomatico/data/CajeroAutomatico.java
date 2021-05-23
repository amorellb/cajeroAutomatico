package com.cajeroAutomatico.data;

import java.util.ArrayList;
import java.util.List;

public class CajeroAutomatico {
    private Integer idUltCajero = 0;
    private Integer idCajero = 0;
    private List<Billetes> billsList = new ArrayList<>();
    private List<Tarjeta> tarjetas = new ArrayList<>();
    private int[][] billetes;

    public CajeroAutomatico() {
    }

    public CajeroAutomatico(Integer idUltCajero, Integer idCajero, List<Billetes> billsList, List<Tarjeta> tarjetas) {
        this.idUltCajero = idUltCajero;
        this.idCajero = idCajero;
        this.billsList = billsList;
        this.tarjetas = tarjetas;
    }

    public CajeroAutomatico(CajeroAutomatico cajeroAutomatico) {
        this.idUltCajero = cajeroAutomatico.idUltCajero;
        this.idCajero = cajeroAutomatico.idCajero;
        this.billsList = cajeroAutomatico.billsList;
        this.tarjetas = cajeroAutomatico.tarjetas;
    }

    public Integer getIdUltCajero() {
        return idUltCajero;
    }

    public void setIdUltCajero(Integer idUltCajero) {
        this.idUltCajero = idUltCajero;
    }

    public Integer getIdCajero() {
        return idCajero;
    }

    public void setIdCajero(Integer idCajero) {
        this.idCajero = idCajero;
    }

    public List<Billetes> getBillsList() {
        return billsList;
    }

    public void setBillsList(List<Billetes> billsList) {
        this.billsList = billsList;
    }

    public List<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(List<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }

    public int[][] getBilletes() {
        return billetes;
    }

    public void setBilletes(int[][] billetes) {
        this.billetes = billetes;
    }
}
