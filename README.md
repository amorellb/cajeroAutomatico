# Práctica 3 - cajeroAutomatico

Actualmente tenemos un cajero automático, en él hay una cantidad limitada de billetes. Esta limitación está bien definida por la siguiente estructura de datos:

~~~
-cajeroBilletes = [[500,10], [200,0], [100,0], [50,30], [20,0], [10,20], [5,25]]
~~~

Es decir, en este estado, tenemos 10 billetes de 500 €, 0 billetes de 200 €, 100 € y 20 €, 30 billetes de 50 € ... Por tanto, en cada una de las sublistas el primer parámetros indica el valor del billete y el segundo la cantidad de billetes.

* Desarrolla las siguientes clases:

    + **Clase CajeroAutomático**, en el que sus principales atributos son:
        + **idUltCaj**: este será el id compartido por todos los cajeros, para poder tener el control del idCajero dado de alta de cada uno de los cajeros.
        + **idCaj**: éste será único e incremental para cada uno de los cajeros que se dan de alta. Se pide que en el momento de la creación se asigne un id de cajero en cajero, teniendo en cuenta el idUltCaj:
        + **billetes**: Tendrá exactamente la estructura de datos definida anteriormente *. Por simplificación, podemos asumir que inicialmente están cargados como se muestra en el ejemplo para cualquier cajero cargado.
        + **tarjeta**: tendrá una lista de tarjetas que se pueden utilizar.
    
    + **Clase Tarjeta**, en el que sus principales atributos son:
        + **NIF** del cliente.
        + **PIN** del cliente.
        + **Nombre** del cliente.
        + **Apellido** del cliente
        + **Estado** de la tarjeta (activada o desactivada)
    
    Además hay dos tipos de tarjeta diferentes que pueden tener los cajeros: (extienden de la clase tarjeta):

    + **Clase TarjetaDebito**, en el que sus principales atributos son:
        **SaldoDisponible**

    + **Clase TarjetaCredito**, en el que sus principales atributos son:
        **SaldoDisponible**, **CreditoDisponible**.

**Desarrolla los constructores (sin parámetros, con parámetros y copia), getters / setters de todas las clases anteriores.**

---

Desarrolla en el programa principal un **menú** únicamente con las siguientes opciones:  

1. Sacar dinero
2. Salir

_Este menú tiene que estar disponible mientras no se utilice la opción de salir. (volveremos a él después de realizar la operación sacar dinero)_

---

* Métodos a desarrollar:
    + **mostrarTarjeta**: Este método es común a las tres clases de tarjeta y tiene que utilizar obligatoriamente la misma firma en los tres métodos (@Override):
    
        1. En la clase tarjeta, imprimirá el nombre, apellido y NIF de la tarjeta en cuestión.
        2. En la clase tarjeta de crédito, además de lo que imprime la clase tarjeta, imprimirá el saldo y el crédito.
        3. En la clase tarjeta de débito, además de lo que imprime la clase tarjeta, imprimirá el saldo.
        4. Haz uso de este método para ver cómo se van actualizando las tarjetas en el menú principal.

    + **mostrarCajero**: Desarrolla un método que haga un recorrido del cajero y muestre el número de billetes disponibles. Utiliza este método antes de imprimir el menú principal, para ver cómo se van actualizando los billetes. Ejemplo:
        
        - 5 billetes de 500 €
        - 0 billetes de 200 €
        - 0 billetes de 100 €
        - 20 billetes de 50 €
        - 0 billetes de 20 €
        - 17 billetes de 10 €
        - 25 billetes de 5 €

    + **sacarDinero**, que se llamará desde el menú principal y realizará todas las siguientes acciones: pedirá un NIF y su PIN. Si encuentra al cliente y el PIN es correcto, pedirá el dinero a retirar. Si no lo encuentra dará un aviso de NIF incorrecto o PIN incorrecto, según corresponda.

_Hay que tener en cuenta que si la tarjeta es de débito solo puede sacar el saldo disponible, y obviamente actualizará el saldo disponible si el cajero tiene billetes por el importe pedido. Si no tiene saldo suficiente, dará un aviso de que no hay saldo suficiente, y por tanto, volverá al menú principal. Si es de crédito podrá sacar el saldo disponible + crédito disponible, y obviamente actualizará el saldo y crédito utilizado disponible si el cajero tiene billetes por el importe solicitado. Si no tiene saldo suficiente, dará un aviso de que no hay saldo suficiente, y por tanto, volverá al menú principal._

**Conclusión: cuando se saque dinero, se actualizará la estructura de datos de tarjetas del cajero con las modificaciones de saldo correspondientes. Utiliza todos los métodos que necesites para encapsular el programa y ahorrar tiempo de desarrollo.**

---

_Ayuda. Utiliza este código en tu programa principal (donde pongas el método main):_

~~~
int[][] carga_billetes = {{500, 1}, {200, 3}, {100, 0}, {50, 0}, {20, 0}, {10, 18}, {5, 25}};
CajeroAutomatico micajero = new CajeroAutomatico();
micajero.setBilletes(carga_billetes);
TarjetaDebito mitarj1 = new TarjetaDebito("12345678a", 1111, "Fran", "Fran", 20000);
TarjetaCredito mitarj2 = new TarjetaCredito("87654321b", 2222, "Javi", "Javi", 1000, 5000);
micajero.getListaTarjeta().add(mitarj1);
micajero.getListaTarjeta().add(mitarj2);
~~~

## Ejemplo de ejecución del programa:

#### DATOS DE LA TARJETA DEL CLIENTE

Nombre : Fran  
Apellido: Fran  
NIF: 12345678a  
Tarjeta de débito  
Saldo: 20000.0

#### DATOS DE LA TARJETA DEL CLIENTE
Nombre : Javi  
Apellido: Javi  
NIF: 87654321b  
Tarjeta de credito  
Saldo: 1000.0  
Crédito: 5000.0  
1 billetes de 500 €  
3 billetes de 200 €  
0 billetes de 100 €  
0 billetes de 50 €  
0 billetes de 20 €  
18 billetes de 10 €  
25 billetes de 5 €  
1. Opcion 1 - Sacar dinero
2. Opcion 2 - Salir  
   
Escribe una de las opciones  
1  
Has seleccionado la opcion 1 - Sacar dinero  
Dame el NIF:  
12345678a  
Dame el PIN  
1111  
¿que cantidad deseas sacar?  
1000  
Desglose de la cantidad satisfecha:  
   1 billetes de 500 €  
   2 billetes de 200 €  
   0 billetes de 100 €  
   0 billetes de 50 €  
   0 billetes de 20 €  
   10 billetes de 10 €  
   
#### DATOS DE LA TARJETA DEL CLIENTE
Nombre : Fran  
Apellido: Fran  
NIF: 12345678a  
Tarjeta de débito  
Saldo: 19000.0  

#### DATOS DE LA TARJETA DEL CLIENTE
Nombre : Javi  
Apellido: Javi  
NIF: 87654321b  
Tarjeta de credito  
Saldo: 1000.0  
Crédito: 5000.0  
   0 billetes de 500 €  
   1 billetes de 200 €  
   0 billetes de 100 €  
   0 billetes de 50 €  
   0 billetes de 20 €  
   8 billetes de 10 €  
   25 billetes de 5 €  
1. Opcion 1 - Sacar dinero
2. Opcion 2 - Salir 
   
Escribe una de las opciones  
2
