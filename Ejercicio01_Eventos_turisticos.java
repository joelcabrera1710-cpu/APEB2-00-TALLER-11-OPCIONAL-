import java.io.File;
import java.util.Formatter;
import java.util.Random;
/**
 * Ejercicios/problemática sobre procesamiento de eventos turísticos.
 * Al ser un tema general, las posibilidades de conceptos 
 * (para las tablas de datos) son infinitas. 
 * 
 * @author Joel Cabrera
 */
public class Ejercicio01_Eventos_turisticos {
    static Random R = new Random();
    static String Dias[] = {"Lunes","Martes","Miercoles","Jueves","Viernes","Sabado","Domingo"};
    static String Ciudades[] ={"Quito","Loja","Catamayo","Vilcabamba"};
    static String Eventos [] = {"Concierto","Desfiles","Fiestas","Teatro"};
    public static void main(String[] args) {
        String R_ciudades[] = new String[7];
        String R_Eventos[]= new String[7];
        int Recaudacion [] = new int [7];
        int R_Asistentes [] = new int [7];
        int R_costo_Entrada [] = new int [7];
        String Impacto[]= new String [7];
        LlenarDatos(R_ciudades, R_Eventos, Recaudacion, R_Asistentes, R_costo_Entrada);
        Impacto(Recaudacion, Impacto);
        PersitirDatos("Eventos_carnaval.csv", R_ciudades, R_Eventos, Recaudacion, R_Asistentes, R_costo_Entrada, Impacto);
    }
    public static void LlenarDatos(String R_Ciudades[], String R_Eventos[],
                    int Recaudacion[], int R_Asistentes [], int R_costo_Entrada []){
        
        for (int i = 0; i < Dias.length; i++) {
            R_Ciudades[i] = Ciudades[R.nextInt(Ciudades.length)];
            R_Eventos[i] = Eventos[R.nextInt(Eventos.length)];
            R_Asistentes[i] = R.nextInt(5000)+100;
            R_costo_Entrada [i] = R.nextInt(100)+5;
            Recaudacion[i] = R_Asistentes[i] * R_costo_Entrada[i];
        }
    }
    
    public static void Impacto (int Recaudacion[], String Impacto[]){
        for (int i = 0; i < Dias.length; i++) {
          if (Recaudacion[i] <= 10000){
              Impacto[i] = "Bajo";
          } else if (Recaudacion[i] <= 50000){
               Impacto[i] = "Medio";
          } else {
              Impacto[i] = "Alto";
          }
        }
    }
    
    public static void PersitirDatos(String Nombre_Archivo, String R_Ciudades[], String R_Eventos[],
                    int Recaudacion[], int R_Asistentes [], int R_costo_Entrada [], String Impacto[]){
        try {
            Formatter fou = new Formatter(new File(Nombre_Archivo));
            fou.format("%s\n","DIA;CIUDAD;EVENTO;ASISTENTES;COSTO_ENTRA;RECAUDACION;IMPACTO");
            for (int i = 0; i < Dias.length; i++) {
            fou.format("%s;",Dias[i]);
            fou.format("%s;",R_Ciudades[i]);
            fou.format("%s;",R_Eventos[i]);
            fou.format("%d;",R_Asistentes[i]);
            fou.format("%d;",R_costo_Entrada[i]);
            fou.format("%d;",Recaudacion[i]);
            fou.format("%s;\n",Impacto[i]);
            }
            fou.close();
        } catch (Exception e) {
            System.out.println("ERROR");
            
        }
    }
    
}
