/**
 * Del ejercicio sobre (Estudiantes VS Calificaciones), 
 * procesar las tabla y/o el archivo de salida, para dar 
 * respuestas a las siguientes preguntas: Cuantos estudiante 
 * aprueban, reprueba, y cuales están por dejado de la media general 
 * (del curso) con que promedio individual 
 * 
 * @author Joel Cabrera
 */
import java.io.File;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;


public class Ejercicio03_Files_procesar_notas {
    public static void main(String[] args) {
        int m=10, n=3;
        String MatrizNotasINP[][] = new String [m][n];
        String MatrizNotasOUT[][] = new String [m][n];
        
        MatrizNotasINP = LeerArchivo_LlenarMatriz("NotassIn.csv", m, n);
        MatrizNotasOUT = ProcesarNotasINP(MatrizNotasINP, MatrizNotasOUT, m, n);
        int Pasaron = ContadorApr(MatrizNotasOUT);
        int Quedados = ContadorRepro(MatrizNotasOUT);
        persistir_resultados("NotasOut.csv",MatrizNotasINP, MatrizNotasOUT, Pasaron, Quedados);


    }
    public static void persistir_resultados (String nombreArchivoOut, String MatrizNotasINP[][], String MatrizNotasOUT[][], int Pasaron, int Quedados){
        try {
            Formatter fou = new Formatter(new File(nombreArchivoOut));
            fou.format("%s\n", "NOMBRES;NOTA1;NOTA2;PROMEDIO;SUPLETORIO;ESTADO");
        for (int i = 0; i < MatrizNotasINP.length; i++) {
            for (int j = 0; j < MatrizNotasINP[0].length; j++) {
                fou.format("%s;", MatrizNotasINP[i][j]);
            }
           for (int j = 0; j < MatrizNotasINP[0].length; j++) {
                fou.format("%s;", MatrizNotasOUT[i][j]);
            }
           fou.format("%s","\n");
        }        
            fou.format("\nAPRO;-;-;-;-;%d\n", Pasaron);
            fou.format("REPRO;-;-;-;-;%d\n", Quedados);
            fou.close();
        } catch (Exception e) {
        }
    }
    public static String [][] ProcesarNotasINP(String MatrizNotasINP[][],
            String datosNotas[][], int m, int n){
            Random ale = new Random();
        for (int i = 0; i < MatrizNotasINP.length; i++) {
            double n1 = Double.valueOf(MatrizNotasINP[i][1]); // o tambien Double.parseDouble();
            double n2 = Double.valueOf(MatrizNotasINP[i][2]);
            double promedio = ( n1 + n2 ) /2;
            double suple = (promedio < 6.5 ) ? ale.nextDouble(10): 0.0 ;
            String estado = (promedio >= 6.5 ) ? "APROBADO" : ( (suple >= 6.5 ) ? "APROBADO" : "REPROBADO") ;
            datosNotas [i][0] = String.format("%.2f",promedio);
            datosNotas [i][1] = String.format("%.2f",suple);
            datosNotas [i][2] = estado;
            }      
        return datosNotas;
    }
    public static String [][] LeerArchivo_LlenarMatriz(String nombreArchivo, int m, int n){
        String datosNotas[][] = new String [m][n];
        int i=0;
        try {
             Scanner fin = new Scanner(new File(nombreArchivo));
             String lineaa;
             lineaa = fin.nextLine();
             while(fin.hasNext()){
                 lineaa = fin.nextLine();
                 String datos [] = lineaa.split(";");
                
                 datosNotas[i][0]= datos[0]; //Nombre est.
                 datosNotas[i][1]= datos[1]; //Nota nota1.
                 datosNotas[i][2]= datos[2]; //Nota nota2.
                 i++;
             }
             
             
        } catch (Exception e) {
            System.out.println("ERROR");
        }
        
        return datosNotas;
    }
// contador quienes aprobaron
    public static int ContadorApr(String MatrizNotasOUT[][] ){
        int aprobados =0;
        for (int i = 0; i < MatrizNotasOUT.length; i++) {
            if(MatrizNotasOUT[i][2].equals("APROBADO")){
                aprobados++;
            }
    }
        return aprobados;
}
    public static int ContadorRepro(String MatrizNotasOUT[][] ){
        int reprobados =0;
        for (int i = 0; i < MatrizNotasOUT.length; i++) {
            if(MatrizNotasOUT[i][2].equals("REPROBADO")){
                reprobados++;
            }
    }
        return reprobados;
}
}