package clima;

public class Pronostico {
    private int dia=0;
    private Boolean lluviaHoy=null;
    private double matrizEstados[][]=null;
    private double resultado;

    
    public Pronostico() {
    }
    
    public Pronostico(double[][] matrizEstados){
        this.matrizEstados = matrizEstados;
    }

    public Pronostico(int dia, boolean lluviaHoy, double[][] matrizEstados) {
        this.dia = dia;
        this.lluviaHoy = lluviaHoy;
        this.matrizEstados = matrizEstados;
    }
    
    
    public boolean ejecutarPronóstico(){
        if(this.dia<=0 || this.lluviaHoy == null || this.matrizEstados == null ){
            return false;
        }else{
            double matrizObjetivo[][] = this.getMatrizEstados(dia);
            int i;
            if(isLluviaHoy())
                i=0;
            else
                i=1;
            this.resultado = matrizObjetivo[i][0];
            return true;
        }
    }
    
    private double[][] getMatrizEstados(int pasos){
        int l = this.matrizEstados.length;
        double m[][] = new double[l][l];
        double mInic[][] = new double[l][l];
        for(int i=0; i<l; i++){
            System.arraycopy(this.matrizEstados[i], 0, m[i], 0, l);
            System.arraycopy(this.matrizEstados[i], 0, mInic[i], 0, l);
        }
        for(int i=0; i<pasos-1; i++){
            m = this.multMatrices(m, mInic);
        } 
        return m;
    }
    
    private double[][] multMatrices(double m[][], double mInic[][]){
        int l = m.length;
        double mAux[][] = new double[l][l];
        for(int i=0; i<l; i++){
            System.arraycopy(m[i], 0, mAux[i], 0, l);
        }
        for(int i=0; i<l; i++){
            for(int j=0; j<l; j++){
                m[i][j] = multMatricialEstado(mAux, mInic, i, j);
            }
        }
        return m;
    }
    
    private double multMatricialEstado(double mAux[][], double mInic[][], int fil, int col){
        int l = mAux.length;
        double r=0;
        for(int i=0; i<l; i++){
            r += mAux[fil][i] * mInic[i][col];
        }
        return r;
    }
    

    public int getDia() {
        return dia;
    }

    public Boolean isLluviaHoy() {
        return lluviaHoy;
    }

    public double[][] getMatrizEstados() {
        return matrizEstados;
    }

    public double getResultado() {
        return resultado;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setLluviaHoy(boolean lluviaHoy) {
        this.lluviaHoy = lluviaHoy;
    }

    public void setMatrizEstados(double[][] matrizEstados) {
        this.matrizEstados = matrizEstados;
    }
    
}
