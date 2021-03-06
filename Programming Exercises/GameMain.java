package catandmousegame;

/**
 *
 * @author Duarte
 */
import java.util.*;

class Mouse {

    Scanner sc = new Scanner(System.in);
    int option = 0, numJuegosJugados = 0, numJuegosGanados = 0, numIntento = 0, puntoX = 0, puntoY = 0, respuestaX = 0, respuestaY = 0;
    static boolean encontrado = false, repetido = false, intentosRestantes = false;
    char respuesta;
    char[][] puntosRepetidos = new char[6][6];
    Random generator = new Random();

    public Mouse() {
        respuestaX = generator.nextInt(6) + 1;
        respuestaY = generator.nextInt(6) + 1;
        puntosRepetidos[respuestaX][respuestaY] = '*';
        //respuestaX = 3; // Hardcode for test
        //respuestaY = 3; // Hardcode for test
        //puntosRepetidos[3][3] = '*'; // Hardcode for test
    }

    public void volverAesconder() {
        respuestaX = generator.nextInt(6) + 1;
        respuestaY = generator.nextInt(6) + 1;
        puntosRepetidos[respuestaX][respuestaY] = '*';
        //respuestaX = 4; // Hardcode for test
        //respuestaY = 4; // Hardcode for test
        //puntosRepetidos[4][4] = '*';
    }

}

class Cat extends Mouse {

    public Cat() {

    }

    public boolean atrapar(int x, int y) {

        System.out.println("\nLos puntos que ingresaste son: " + x + "," + y);

        // COMPARAR SI YA ESTA OCUPADO
        if (puntosRepetidos[x][y] == '*') {
            encontrado = true;
        } else if (puntosRepetidos[x][y] == '\0') {
            //System.out.println("Esta vacio");
            puntosRepetidos[x][y] = '+';
            System.out.println("El raton no esta aqui.");
        } else {
            System.out.println("Ya haz ingresado estos puntos. Intenta con otros.");
            numIntento--;
            repetido = true;
        }

        // IMPRIMIR LA MATRIZ PARA VER 
        /*
        for(int i=1;i <= puntosRepetidos.length - 1;i++)
        {
            for(int j=1;j <= puntosRepetidos.length - 1;j++)
            {
                System.out.print(puntosRepetidos[i][j]+"\t");
            }
            System.out.println();
        }
         */
        return encontrado;
    }

}

class GameLogic extends Cat {

    public void mostrarInstrucciones() {
        System.out.println("Bienvenuto al juego del gato y el raton. Las instrucciones son sencillas, "
                + "\ntienes 5 oportunidades antes de que el raton se escape."
                + "\nTienes que ingresar coordenadas en formato X,Y."
                + "\nEl tablero es una matriz de 5 por 5"
                + "\n*****"
                + "\n*****"
                + "\n*****"
                + "\n*****"
                + "\n*****\n\n");
    }

    public void limpiarConsola() {
        /*
        for (int i = 0; i < 30; ++i) {
            System.out.println();
        }
        this.mostrarInstrucciones();
         */
    }

    public void run() {
        this.mostrarInstrucciones();

        try {
            System.out.println("\nDo you wanna play? ");
            System.out.println("\nIngresa la opcion deseada (1 - Jugar, 2 - Salir)");
            option = sc.nextInt();
            sc.nextLine();
        } catch (Exception ex) {
            System.out.println("No ingresaste una opcion valida.");
            System.exit(0);
        }

        switch (option) {
            case 1:
                play();
            case 2:
                System.out.println("Bueno, ciao, ciao!");
                System.exit(0);
            default:
                System.out.println("Opcion incorrecta. Exiting!!!!");
                System.exit(0);
        }
    }

    public void play() {

        numJuegosJugados++;

        do {
            limpiarConsola();
            numIntento++;
            System.out.println("\n***********************************************************");
            System.out.println("Este es tu " + numIntento + " intento. Good luck!");
            //System.out.println("La respuesta correcta es " + respuestaX + " , " + respuestaY);
            try {
                System.out.println("Que punto quieres para X?");
                puntoX = sc.nextInt();
                sc.nextLine();
                System.out.println("Que punto quieres para Y?");
                puntoY = sc.nextInt();
                sc.nextLine();
            } catch (Exception ex) {
                System.out.println("No ingresaste una opcion valida.");
                System.exit(0);
            }

            encontrado = atrapar(puntoX, puntoY);

            if (encontrado) {
                intentosRestantes = false;
            } else if (numIntento < 5) {
                intentosRestantes = true;
            } else {
                intentosRestantes = false;
            }

        } while (intentosRestantes == true);

        if (encontrado == true) {
            numJuegosGanados++;
            System.out.println("\nGreat!, haz encontrado al raton!! \nEstaba escondido en el punto ("
                    + respuestaX + " , " + respuestaY + ")");
        } else {
            System.out.println("Sorry, no encontraste al raton esta vez, intenta otra vez.");
        }

        try {
            System.out.println("\nQuieres seguir jugando (Y/N)?");
            respuesta = sc.next().charAt(0);
            sc.nextLine();
        } catch (Exception ex) {
            System.out.println("\nNo ingresaste una opcion valida.");
            System.exit(0);
        }

        limpiarConsola();
        if (respuesta == 'Y' || respuesta == 'y') {
            System.out.println("Volvamos al juego");
            jugarOtraVez();
        } else if (respuesta == 'N' || respuesta == 'n') {
            System.out.println("Jugaste: " + numJuegosJugados + ". Haz ganado: " + numJuegosGanados);
            System.out.println("Ciao amici");
        } else {
            System.out.println("No ingresaste una opcion valida. Exiting!!!!");
        }

    }

    public void jugarOtraVez() {
        numIntento = 0;
        respuestaX = 0;
        respuestaY = 0;
        puntoX = 0;
        puntoY = 0;
        encontrado = false;
        repetido = false;
        intentosRestantes = false;
        option = 0;
        puntosRepetidos = new char[6][6];
        volverAesconder();
        play();
    }

    public void exit() {
        System.exit(0);
    }

}

public class GameMain {

    public static void main(String[] args) {

        GameLogic logica = new GameLogic();
        logica.run();

    }

}
