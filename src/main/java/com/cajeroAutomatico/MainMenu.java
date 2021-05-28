package com.cajeroAutomatico;

import com.cajeroAutomatico.data.CajeroAutomatico;
import com.cajeroAutomatico.data.TarjetaCredito;
import com.cajeroAutomatico.data.TarjetaDebito;
import com.cajeroAutomatico.service.AtmService;
import com.cajeroAutomatico.service.ClientInput;

public class MainMenu {
    public static void main(String[] args) {
        CajeroAutomatico cajero = AtmService.inicializarCajero(new CajeroAutomatico());

        int[][] carga_billetes = {{500, 10}, {200, 20}, {100, 40}, {50, 80}, {20, 100}, {10, 100}, {5, 500}};
        cajero.setBilletes(carga_billetes);

        TarjetaDebito tarjetaDebito = new TarjetaDebito("12345678a", 1111, "Miquel", "Smith", 20000);
        TarjetaCredito tarjetaCredito = new TarjetaCredito("87654321b", 2222, "Bernat", "Martorell", 2000, 5000);

        cajero.getTarjetas().add(tarjetaDebito);
        cajero.getTarjetas().add(tarjetaCredito);

        System.out.println("Información de la tarjeta de débito:");
        System.out.println("------------------------------------");
        tarjetaDebito.mostrarTarjeta();
        System.out.println("------------------------------------");
        System.out.println(" ");
        System.out.println("Información de la tarjeta de crédito:");
        System.out.println("-------------------------------------");
        tarjetaCredito.mostrarTarjeta();
        System.out.println("-------------------------------------");
        System.out.println(" ");
        System.out.println("Información del cajero:");
        System.out.println("-----------------------");
        AtmService.mostrarCajero(cajero);
        System.out.println("-----------------------");
        System.out.println(" ");

        System.out.println("========================");
        System.out.println("Operaciones disponibles:");
        System.out.println("- Sacar dinero");
        System.out.println("- Salir");
        System.out.println("========================");

        boolean salir = false;
        while (!salir) {
            String operation;
            do {
                operation = ClientInput.askOperation().toLowerCase();
                if (operation.equals("salir")) {
                    System.out.println(" ");
                    System.out.println("Gracias por elegir nuestros servicios");
                    salir = true;
                }
            } while (!operation.equals("sacar dinero") && !salir);

            if (!salir) {
                int amount = ClientInput.askAmount();
                String nif = ClientInput.askNif(cajero);
                int pin = ClientInput.askPin(cajero, nif);

                if (tarjetaDebito.getClientNif().equals(nif) && tarjetaDebito.getClientPin().equals(pin)) {
                    // AtmService.sacarDineroDebito(cajero, tarjetaDebito, amount);
                    AtmService.sacarDebitoArray(cajero, tarjetaDebito, amount);
                } else if (tarjetaCredito.getClientNif().equals(nif) && tarjetaCredito.getClientPin().equals(pin)) {
                    // AtmService.sacarDineroCredit(cajero, tarjetaCredito, amount);
                    AtmService.sacarCreditArray(cajero, tarjetaCredito, amount);
                }
            }
        }
    }
}
