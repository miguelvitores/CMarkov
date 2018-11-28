package clima;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Clima {
    private static double PROB_LL=0.8;//Probabilidad de que hoy llueva y mañana también
    private static double PROB_SL=0.6;//Probabilidad de que hoy no llueva y mañana sí
    private static double PROB_LS=1-PROB_LL;//Probabilidad de que hoy llueva y mañana no
    private static double PROB_SS=1-PROB_SL;//Probabilidad de que hoy no llueva y mañana tampoco
    
    public static void main(String[] args) {
        boolean salir = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int opcion;
        double matrizEstados[][] = {
            {PROB_LL, PROB_LS},
            {PROB_SL, PROB_SS}
        };
        
        Pronostico p = new Pronostico(matrizEstados);
        do{
            try {
                System.out.print(menu());
                opcion = Integer.parseInt(br.readLine());
                switch(opcion){
                    case 0:
                        salir = true;
                        break;
                    case 1:
                        System.out.print("Día: ");
                        p.setDia(Integer.parseInt(br.readLine()));
                        System.out.println("Establecido día "+p.getDia());
                        break;
                    case 2:
                        p.setLluviaHoy(true);
                        System.out.println("Hoy llovío");
                        break;
                    case 3:
                        p.setLluviaHoy(false);
                        System.out.println("Hoy no llovío");
                        break;
                    case 4:
                        if( p.ejecutarPronóstico() ){
                            System.out.println("Probabilidad de que llueva dentro de "+
                                    p.getDia()+" días: "+p.getResultado());
                            salir = true;
                        }else{
                            System.out.println("No se han rellenado todos los campos");
                        }
                        break;
                    default:
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }while( ! salir );
    }
    
    private static String menu(){
        return    "---------------------------"
                + "\nMENÚ EJEMPLO CLIMA"
                + "\n0.Salir"
                + "\n1.Establecer día pronóstico"
                + "\n2.LLUVIA hoy"
                + "\n3.SECO hoy"
                + "\n4.Ejecutar pronóstico"
                + "\nOpción: ";
    }
    
}
