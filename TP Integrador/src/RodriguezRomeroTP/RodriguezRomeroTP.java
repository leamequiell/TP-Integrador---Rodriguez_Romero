package RodriguezRomeroTP;

import java.util.Scanner;
import java.util.InputMismatchException;

public class RodriguezRomeroTP {

	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Declaración de variables globales
        boolean salaLlena = false;
        int ingresosTotales = 0;

        // Arrays para representar la ocupación de asientos
        boolean[] ocupadoFilaA = new boolean[4];
        boolean[] ocupadoFilaB = new boolean[4];
        boolean[] ocupadoFilaC = new boolean[4];

        // Bucle principal
        while (!salaLlena) {
            System.out.print("Ingrese la fila (A, B o C): ");
            String fila = scanner.nextLine().toLowerCase();

            try {
                // Verificar si la fila está llena
                boolean filaLlena = false;
                switch (fila) {
                    case "a":
                        filaLlena = checkFilaLlena(ocupadoFilaA);
                        break;
                    case "b":
                        filaLlena = checkFilaLlena(ocupadoFilaB);
                        break;
                    case "c":
                        filaLlena = checkFilaLlena(ocupadoFilaC);
                        break;
                    default:
                        throw new InputMismatchException("Fila no válida. Debe ser A, B o C.");
                }

                if (filaLlena) {
                    System.out.println("La fila está llena. Elija otra fila.");
                    continue;
                }

                System.out.print("Ingrese el número de asiento (1-4): ");
                int numeroAsiento = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                if (numeroAsiento < 1 || numeroAsiento > 4) {
                    throw new InputMismatchException("Número de asiento no válido. Debe estar entre 1 y 4.");
                }

                // Verificar si el asiento está ocupado
                boolean asientoOcupado = false;
                switch (fila) {
                    case "a":
                        asientoOcupado = ocupadoFilaA[numeroAsiento - 1];
                        break;
                    case "b":
                        asientoOcupado = ocupadoFilaB[numeroAsiento - 1];
                        break;
                    case "c":
                        asientoOcupado = ocupadoFilaC[numeroAsiento - 1];
                        break;
                }

                if (asientoOcupado) {
                    System.out.println("El asiento está ocupado. Elija otro asiento.");
                } else {
                    // Vender la entrada
                    int precio = getPrecioFila(fila);
                    System.out.println("Asiento " + fila.toUpperCase() + numeroAsiento + " vendido por $" + precio);
                    ingresosTotales += precio;

                    // Marcar el asiento como ocupado
                    switch (fila) {
                        case "a":
                            ocupadoFilaA[numeroAsiento - 1] = true;
                            break;
                        case "b":
                            ocupadoFilaB[numeroAsiento - 1] = true;
                            break;
                        case "c":
                            ocupadoFilaC[numeroAsiento - 1] = true;
                            break;
                    }
                }

                // Verificar si la sala está llena
                salaLlena = checkSalaLlena(ocupadoFilaA, ocupadoFilaB, ocupadoFilaC);
            } catch (InputMismatchException e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Limpiar el buffer de entrada
            }
        }

        System.out.println("¡Sala llena!");
        System.out.println("Ingresos totales: $" + ingresosTotales);
    }

    // Función para verificar si una fila está llena
    public static boolean checkFilaLlena(boolean[] fila) {
        for (boolean ocupado : fila) {
            if (!ocupado) {
                return false;
            }
        }
        return true;
    }

    // Función para verificar si la sala está llena
    public static boolean checkSalaLlena(boolean[] filaA, boolean[] filaB, boolean[] filaC) {
        return checkFilaLlena(filaA) && checkFilaLlena(filaB) && checkFilaLlena(filaC);
    }

    // Función para obtener el precio de una fila
    public static int getPrecioFila(String fila) {
        switch (fila) {
            case "a":
                return 500;
            case "b":
                return 700;
            case "c":
                return 1000;
            default:
                return 0; // Fila no válida
        }
    }
	/*
	 * PRIMERA ETAPA - OCUPACIÓN DE ASIENTOS - Ingresar primero la fila, es decir,
	 * una letra de la A a la C. Luego, ingresar un número entero para el número de
	 * asiento. - Si un cliente desea un asiento determinado (por ejemplo, C2), el
	 * programa debe informarle si está ocupado o no. Para determinar si está
	 * ocupado o no, pueden generar un array o una matriz que almacene todos estos
	 * "false" (se puede llamar asientoOcupado por ejemplo). Tengan en cuenta que en
	 * esta etapa todos los asientos van a estar desocupados. Pero programamos todo
	 * esto para las etapas siguientes. CONSEJOS: - Pueden asociar cada array de
	 * cada fila con una letra. Ejemplo: asientosA, asientosB, asientosC. También,
	 * pueden generar una matriz de 4x3. - Almacenen POR PROGRAMA todos los "false"
	 * de los asientos ocupados. Armen el array, si usaron arrays para los asientos,
	 * o armen matrices si usaron matrices. - Compilen esta etapa. Luego de que la
	 * etapa esté bien programada, continúan con la siguiente. - Si fuera necesario,
	 * miren programas donde hayan usado if, while, for, switch, arrays, matrices y
	 * funciones.
	 */

	/*
	 * SEGUNDA ETAPA - VENTAS - Si un asiento está ocupado, hay que volver a
	 * ingresar otro asiento (Ejemplos: B1, A2, C3, etc.). Cada vez que se ingrese
	 * un asiento ocupado, volver a preguntar hasta que se pueda encontrar un
	 * asiento libre.- Si el asiento NO está ocupado, según la fila ingresada, hay
	 * que venderle la entrada al cliente, y ocupar el asiento. Recuerden: La fila A
	 * tiene un costo de $500, la B tienen un costo de $700, y la C de $ 1000. - Si
	 * una fila se llenó, indicar por pantalla que la fila está llena. - Cada vez
	 * que se venda una entrada, hay que acumular en alguna variable
	 * "ingresosTotales" al total vendido. CONSEJOS: - Almacenen el precio de las
	 * entradas en el tipo de dato que deseen: variables, arrays o matrices. -
	 * Compilen esta etapa. Luego de que la etapa esté bien programada, continúan
	 * con la siguiente. - Si fuera necesario, miren programas donde hayan usado if,
	 * while, for, switch, arrays, matrices y funciones.
	 */

	/*
	 * TERCERA ETAPA - SALA LLENA - Hay que agregar algo al programa para que, si
	 * todos asientos están ocupados, indicar por pantalla "sala llena". - Ahora, al
	 * llenar una fila, el programa no debe finalizar. Sino que debe volver a
	 * preguntar qué fila desea la persona. - Además, hay que mostrar por pantalla
	 * los ingresos totales, almacenados en la etapa anterior. CONSEJOS: - Compilen
	 * esta etapa. Luego de que la etapa esté bien programada, continúan con la
	 * siguiente.
	 */

	/*
	 * CUARTA ETAPA- AFINANDO DETALLES Para que el programa sea mas eficiente, hay
	 * que capturar errores. Para ello, capturen los errores: - Cuando se intente
	 * ingresar números enteros y se ingrese otro tipo de dato (un número decimal,
	 * un char, un string, etc.), informar el inconveniente y volver a solicitar el
	 * dato esperado. Recordá que esto es una excepción del tipo
	 * InputMismatchException. - Si el boletero ingresa un símbolo que no sea A, B o
	 * C, capturar la excepción del tipo InputMismatchException. Cuidado, acá hay
	 * que arrojar una nueva excepción... - Si el boletero ingresa un número
	 * negativo, o mayor a X, capturar la excepción del tipo InputMismatchException.
	 * Cuidado, acá hay que arrojar una nueva excepción...
	 */

	/*
	 * public static void main(String[] args) { Scanner entrada = new Scanner
	 * (System.in); int columna; char fila; boolean[][] matriz1 = new boolean[3][4];
	 * 
	 * [][] matriz1 = new boolean[3][4];
	 * 
	 * System.out.println("ingrese la fila que desea a ocupar (A, B, C)"); fila =
	 * entrada.next().charAt(0);
	 * 
	 * 
	 * 
	 * switch(fila) {
	 * 
	 * case 'A': System.out.println("quedan 5 remeras"); break;
	 * 
	 * case 'B': System.out.println("quedan 2 remeras"); break;
	 * 
	 * case 'C': System.out.println("no quedan remeras"); break;
	 * 
	 * default: System.out.
	 * println("el valor introducido no esta dentro del rango de numeros aceptados."
	 * ); break; }
	 * 
	 * 
	 * 
	 * 
	 * System.out.println("ingrese la columna que desea a ocupar (1, 2, 3,4)");
	 * columna = entrada.next().charAt(0);
	 * 
	 * switch(columna) {
	 * 
	 * case 1 : System.out.println("quedan 5 remeras"); break;
	 * 
	 * case 2 : System.out.println("quedan 2 remeras"); break;
	 * 
	 * case 3 : System.out.println("no quedan remeras"); break; case 4 :
	 * System.out.println("no quedan remeras"); break;
	 * 
	 * default: System.out.
	 * println("el valor introducido no esta dentro del rango de numeros aceptados."
	 * ); break; }
	 * 
	 * 
	 */

}
