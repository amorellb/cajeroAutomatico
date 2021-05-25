package com.cajeroAutomatico;

import com.cajeroAutomatico.data.CajeroAutomatico;
import com.cajeroAutomatico.data.TarjetaCredito;
import com.cajeroAutomatico.data.TarjetaDebito;
import com.cajeroAutomatico.service.AtmService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AtmServiceTest {

    private static CajeroAutomatico cajero;
    private static TarjetaDebito tarjetaDebito;
    private static TarjetaCredito tarjetaCredito;


    @BeforeAll
    static void createCajero() {
        cajero = new CajeroAutomatico();
        tarjetaDebito = new TarjetaDebito("12345678a", 1111, "Miquel", "Smith", 20000);
        tarjetaCredito = new TarjetaCredito("87654321b", 2222, "Bernat", "Martorell", 2000, 5000);
    }

    @Test
    void inicializarCajeroCreatesNewObject() {
        assertNotSame(cajero, AtmService.inicializarCajero(cajero));
    }

    @Test
    void sacarDineroDebitoSaldoTarjeta() {
        AtmService.sacarDineroDebito(cajero, tarjetaDebito, 10000);
        assertEquals(9000, tarjetaDebito.getSaldoDisponible());
    }
}
