/**
 * El mismo ejercicio de pero de diferentes artistas que se presentan de distintos dias
 * @author Joel Cabrera
 */
import java.util.Random;
import java.io.File;
import java.util.Formatter;
public class Ejercicio02_Matriz_Eventos_Artistas {
    static Random R = new Random();
    static String Dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    static String Opciones[] = new String [7];
    static String Artistas[] = {"Mon laferte","Paulo Londra","Luis Miguel"};
    
    
    public static void Completar(int Recaudacion[], int Asistentes[], int Costo_Entrada[]){
        for (int i = 0; i < Dias.length; i++) {
            Opciones[i] = Artistas[R.nextInt(Artistas.length)];
            Asistentes[i] = R.nextInt(100000) + 1000;
            Costo_Entrada[i] = R.nextInt(100) + 10;
            Recaudacion[i] = Asistentes[i] * Costo_Entrada[i]; 
        }
    }
    public static int max_asistentes(int Asistentes[]){
        int mayor1, posicion = 0;
        mayor1 = Asistentes[0];
        for (int i = 0; i < Asistentes.length; i++) {
            if(Asistentes[i] > mayor1){
                mayor1 = Asistentes[i];
                posicion = i;
            }
        }
        System.out.println("El evento que mas asistentes tuvo fue el del: "+ Dias[posicion]+ 
                " con "+ Opciones[posicion]+ " un num de asistentes de: "+ mayor1 );
        return mayor1;
    }
    
    
    public static int max_recaudacion(int Recaudacion[]){
        int mayor2, posicion = 0;
        mayor2 = Recaudacion[0];
        for (int i = 0; i < Recaudacion.length; i++) {
            if(Recaudacion[i] > mayor2){
               mayor2 = Recaudacion[i];
               posicion = i;
            }
        }
        System.out.println("El evento que mas recaudo  fue el del: "+ Dias[posicion]+ 
                " con "+ Opciones[posicion]+  " una cantidad de: "+ mayor2 ); 
        return mayor2;
    }
    public static void PersistirResultados(String Nombre_Archivo,int Recaudacion[], int Asistentes[],
            int Costo_Entrada[], int mayor1, int mayor2){
        try {
             Formatter fou = new Formatter(new File(Nombre_Archivo)); 
             fou.format("%s\n","DIA;ARTISTA;ASISTENTES;CT_ENTR;RECAUDA");
             for (int i = 0; i < Dias.length; i++) {
                fou.format("%s;", Dias[i] );
                fou.format("%s;", Opciones[i] );
                fou.format("%d;", Asistentes[i] );
                fou.format("%d;", Costo_Entrada[i] );
                fou.format("%d;\n", Recaudacion[i] );
            }
             fou.format("\nMas_asist;-;-;-;%d\n", mayor1);
             fou.format("Mas_reca;-;-;-;%d\n", mayor2);
             fou.close();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        
    }
    
    
    public static void main(String[] args) {
        int Recaudacion[] = new int [7];
        int Asistentes[] = new int[7];
        int Costo_Entrada[] = new int [7];
        Completar(Recaudacion, Asistentes, Costo_Entrada);

        int mayorAsi = max_asistentes(Asistentes);
        int mayorReca = max_recaudacion(Recaudacion);
        PersistirResultados("Artistas.csv", Recaudacion, Asistentes, Costo_Entrada,mayorAsi,mayorReca);
    }
}
