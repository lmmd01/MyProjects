import java.util.*;

public class Maquinita {
public static void main(String[] args) {
    
    int apuesta = 0, balanceRestante = 0,juegosDoblesGanados = 0, juegosTriplesGanados = 0;
    int slot1 = 0, slot2 = 0, slot3 = 0;
    boolean seguirJugando = true;
    char respuesta;

    Random generator = new Random();
    
    // Falta validar que ingrese cantidades enteras y validas (no menos de cero y no letras)
    System.out.println("Tu balance es: $" + balanceRestante);
    Scanner teclado=new Scanner(System.in);
    System.out.println("Ingresa la cantidad a jugar $: ");
    apuesta = teclado.nextInt();
    balanceRestante =+ apuesta;
    System.out.println("Muy bien, ahora tienes $: " + balanceRestante);

  
    while (seguirJugando == true && balanceRestante > 0){
        slot1 = generator.nextInt(10);
        slot2 = generator.nextInt(10);
        slot3 = generator.nextInt(10);
        System.out.println("Haz obtenido las siguientes combinaciones: " 
                + slot1 + "---" + slot2 + "---" + slot3);
        
        if(slot1 == slot2 && slot2 == slot3 && slot1 == slot3){
            balanceRestante += 10;
            System.out.println("Hiciste triple. Ahora tienes : " + balanceRestante);
            juegosTriplesGanados++;
        }else if (slot1 == slot2 || slot2 == slot3 || slot1 == slot3  ){
            balanceRestante += 5;
            System.out.println("Hiciste doble. Ahora tienes : " + balanceRestante);
            juegosDoblesGanados++;
        }else{
            balanceRestante -= 5;
            System.out.println("Not this time, my friend. Te quedan: " + balanceRestante + " pesos");
        }
        
         // Falta validar que solo pueda ingresar Y o N 
        System.out.println("Quieres seguir jugando? (Y/N)");
        //respuesta = teclado.next().charAt(0);
        respuesta = 'Y'; // Temp hasta que funcione el charAt()
    
        if(respuesta == 'Y' || respuesta == 'y'){
            seguirJugando = true;
            
             // Falta validar que ingrese cantidades enteras y validas (no menos de cero y no letras)
            while (balanceRestante <= 0){
                System.out.println("Ingresa la cantidad a jugar $: ");
    //apuesta = teclado.nextInt();
    apuesta = 5; // Temp hasta que funcione el nextInt()
    balanceRestante =+ apuesta;
    System.out.println("Muy bien, ahora tienes $: " + balanceRestante);
            }
            
        } else {
            seguirJugando = false;
            System.out.println("El balance que te sobra es: " + balanceRestante + ". Haz ganado: " + juegosDoblesGanados + " juegos dobles y " + juegosTriplesGanados + " juegos triples." );
        }
    }
    if(balanceRestante <= 0){
        System.out.println("Ya no tienes creditos");
        System.out.println("El balance que te sobra es: " + balanceRestante + ". Haz ganado: " + juegosDoblesGanados + " juegos dobles y " + juegosTriplesGanados + " juegos triples." );
        System.out.println("Ciao amici");
    }
}
}