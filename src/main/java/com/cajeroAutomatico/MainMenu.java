package com.cajeroAutomatico;

import com.cajeroAutomatico.data.CajeroAutomatico;
import com.cajeroAutomatico.data.TarjetaCredito;
import com.cajeroAutomatico.data.TarjetaDebito;
import com.cajeroAutomatico.service.AtmService;
import com.cajeroAutomatico.service.Utils;

public class MainMenu {
    public static void main(String[] args) {
        CajeroAutomatico cajero = AtmService.inicializarCajero(new CajeroAutomatico());

        int[][]  carga_billetes = {{500, 2}, {200, 5}, {100, 5}, {50, 5}, {20, 10}, {10, 10}, {5, 20}};
        cajero.setBilletes(carga_billetes);

        TarjetaDebito tarjetaDebito = new TarjetaDebito("12345678a", 1111, "Miquel", "Sureda", 20000);
        TarjetaCredito tarjetaCredito = new TarjetaCredito("87654321b", 2222, "Bernat", "Martorell", 2000, 5000);

        cajero.getTarjetas().add(tarjetaDebito);
        cajero.getTarjetas().add(tarjetaCredito);

        // Mostrar cajero y tarjetas
        AtmService.mostrarCajero(cajero);
        tarjetaDebito.mostrarTarjeta();
        tarjetaCredito.mostrarTarjeta();

        

    }
}
