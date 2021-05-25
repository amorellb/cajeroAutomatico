package com.cajeroAutomatico;

import com.cajeroAutomatico.data.CajeroAutomatico;
import com.cajeroAutomatico.data.TarjetaCredito;
import com.cajeroAutomatico.data.TarjetaDebito;
import com.cajeroAutomatico.service.Utils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UtilsTest {

    private static CajeroAutomatico cajero;

    @BeforeAll
    static void createCajero() {
        cajero = new CajeroAutomatico();
        TarjetaDebito tarjetaDebito = new TarjetaDebito("12345678a", 1111, "Miquel", "Smith", 20000);
        TarjetaCredito tarjetaCredito = new TarjetaCredito("87654321b", 2222, "Bernat", "Martorell", 2000, 5000);

        cajero.getTarjetas().add(tarjetaDebito);
        cajero.getTarjetas().add(tarjetaCredito);
    }

    @Test
    void checkOperationSalirTrue() {
        assertTrue(Utils.checkOperation("salir"));
    }

    @Test
    void checkOperationSacarDineroTrue() {
        assertTrue(Utils.checkOperation("sacar dinero"));
    }

    @Test
    void checkOperationFalse() {
        assertFalse(Utils.checkOperation("Hello world"));
    }

    @Test
    void checkNifTrue() {
        assertTrue(Utils.checkNif(cajero, "12345678a"));
    }

    @Test
    void checkNifFalse() {
        assertFalse(Utils.checkNif(cajero, "Hello world"));
    }

    @Test
    void checkPinTrue() {
        assertTrue(Utils.checkPin(cajero, 1111));
    }

    @Test
    void checkPinFalse() {
        assertFalse(Utils.checkPin(cajero, 666));
    }

    @Test
    void isIntTrue() {
        assertTrue(Utils.isInt("1"));
    }

    @Test
    void isIntFalse() {
        assertFalse(Utils.isInt("Hello world"));
    }
}
