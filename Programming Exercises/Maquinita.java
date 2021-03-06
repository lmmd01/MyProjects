
import java.util.*;

public class Maquinita {

    int juegos = 0, numJuegos = 0, juegosDoblesGanados = 0, juegosTriplesGanados = 0, puntaje = 0, contador = 0;
    int slot1 = 0, slot2 = 0, slot3 = 0;
    boolean seguirJugando = true;
    char respuesta;
    Random generator = new Random();
    Scanner teclado = new Scanner(System.in);

    public void play() {

        try {
            System.out.println("\nTienes: " + numJuegos + " juegos.");
            System.out.println("Cuantos juegos quieres jugar?");
            juegos = teclado.nextInt();
            //Limpiamos buffer de entrada
            teclado.nextLine();
            numJuegos = +juegos;

        } catch (Exception ex) {
            System.out.println("Error. No haz ingresado un numero valido. \nExiting.......");
        }

        this.limpiarConsola();

        while (seguirJugando == true && numJuegos > 0) {
            numJuegos--;
            contador++;
            System.out.println("Este es tu : " + contador + " juego.");
            slot1 = generator.nextInt(10);
            slot2 = generator.nextInt(10);
            slot3 = generator.nextInt(10);
            System.out.println("\nHaz obtenido las siguientes combinaciones: " + slot1 + "---" + slot2 + "---" + slot3);

            if (slot1 == slot2 && slot2 == slot3 && slot1 == slot3) {
                puntaje += 10;
                System.out.println("Hiciste triple. Ahora tienes : " + puntaje + " puntos.");
                juegosTriplesGanados++;
                System.out.println("Te quedan: " + numJuegos + " juegos restantes.");
            } else if (slot1 == slot2 || slot2 == slot3 || slot1 == slot3) {
                puntaje += 5;
                System.out.println("Hiciste doble. Ahora tienes : " + puntaje + " puntos.");
                juegosDoblesGanados++;
                System.out.println("Te quedan: " + numJuegos + " juegos restantes.");
            } else {
                System.out.println("Not this time, my friend. Tu puntaje es : " + puntaje);
                System.out.println("Te quedan: " + numJuegos + " juegos restantes.\n");
            }

            // Falta validar que solo pueda ingresar Y o N 
            System.out.println("Quieres seguir jugando? (Y/N)");
            respuesta = teclado.next().charAt(0);
            teclado.nextLine();
            this.limpiarConsola();

            if (respuesta == 'Y' || respuesta == 'y') {
                seguirJugando = true;
                // Falta validar que ingrese cantidades enteras y validas (no menos de cero y no letras)
                if (numJuegos < 1) {
                    try {
                        System.out.println("Ya no tienes juegos.");
                        System.out.println("Cuantos juegos quieres agregar?");
                        juegos = teclado.nextInt();
                        teclado.nextLine();
                        numJuegos = +juegos;
                        System.out.println("Muy bien, ahora tienes : " + numJuegos + " juegos.");
                    } catch (Exception ex) {
                        System.out.println("Error. No haz ingresado un numero valido. \nExiting.......");
                    }
                }
            } else if (respuesta == 'N' || respuesta == 'n') {
                seguirJugando = false;
                System.out.println("Tienes: " + numJuegos + " juegos por jugar. Haz jugado: " + contador + " veces. Haz ganado: " + juegosDoblesGanados + " juegos dobles y " + juegosTriplesGanados + " juegos triples. Tu puntaje es " + puntaje);
                if (numJuegos > 0) {
                    System.out.println("Para reaunudar el juego, presiona Y o N para salir de la aplicacon.");
                    respuesta = teclado.next().charAt(0);
                    teclado.nextLine();
                    switch (respuesta) {
                        case 'Y':
                            seguirJugando = true;
                            this.limpiarConsola();
                            break;

                        case 'y':
                            seguirJugando = true;
                            this.limpiarConsola();
                            break;

                        case 'N':
                            System.out.println("All rigth then. Exiting.......");
                            System.exit(0);
                            break;

                        case 'n':
                            System.out.println("All rigth then. Exiting.......");
                            System.exit(0);
                            break;

                        default:
                            System.out.println("Error. No haz ingresado una opcion valida. Exiting.......");
                            System.exit(0);
                            break;
                    }
                }
            } else {
                System.out.println("Error. No haz ingresado una opcion valida. Exiting.......");
                System.exit(0);
            }
        }

    }

    public void start() {
        //INSTRUCCIONES
        try {
            this.mostrarInstrucciones();
            System.out.println("Quieres jugar?");
            respuesta = teclado.next().charAt(0);
            teclado.nextLine();
        } catch (Exception ex) {
            System.out.println("Error. No haz ingresado una opcion valida. Exiting.......");
        }

        if (respuesta == 'Y' || respuesta == 'y') {
            this.play();
        } else if (respuesta == 'N' || respuesta == 'n') {
            // Salir de la aplicacion
            System.out.println("All rigth then. Exiting.......");
            System.exit(0);
        } else {
            System.out.println("Error. No haz ingresado una opcion valida. Exiting.......");
            System.exit(0);
        }
    }

    public void mostrarInstrucciones() {
        //INSTRUCCIONES
        System.out.println("**************************************************************"
                + "\nBienvenido al juego, las instrucciones son las siguientes:"
                + "\n-Ingresa el numero de juegos que quieres jugar."
                + "\n-Si los 3 numeros caen iguales, obtienes 10 puntos. Si 2 numeros caen iguales, son 5 puntos."
                + "\n-Al final de cada juego, podras ver tu score y el numero de juegos que te restan. Ademas podras agregar mas juegos"
                + "\n-Pulsa 'Y' para jugar y pulsa 'N' para salir."
                + "\n**************************************************************\n");
    }

    public void limpiarConsola() {
        for (int i = 0; i < 30; ++i) {
            System.out.println();
        }
        this.mostrarInstrucciones();
    }

    public static void main(String[] args) {
        Maquinita maquis = new Maquinita();
        maquis.start();
    }

}
