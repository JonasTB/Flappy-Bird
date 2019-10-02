package Model;

import java.util.HashMap;

/**
 * Cores em RGB
 */
public class Cor
{
    public int r;
    public int g;
    public int b;
    
    private static HashMap<Integer, Cor> cores = new HashMap<Integer, Cor>();
    
    // Cria cores;
    private Cor(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    // Executa as cores de 0 a 1;
    public static Cor rgb(double r, double g, double b) {
        return Cor.rgb((int)(r*255), (int)(g*255), (int)(b*255));
    }

    // Chama o mesmo metodo, mas com as diferencas de indices;
    public static Cor rgb(int r, int g, int b) {

        int indice = (r << 16) | (g << 8) | b;

        if(!cores.containsKey(indice)) {

            cores.put(indice, new Cor(r, g, b));
        }

        return cores.get(indice);
    }

    // RGB;
    public static Cor branco = Cor.rgb(1.0, 1.0, 1.0);
    public static Cor azul = Cor.rgb(0.0, 0.0, 1.0);
    public static Cor vermelho = Cor.rgb(1.0, 0.0, 0.0);
    public static Cor verde = Cor.rgb(0.0, 1.0, 0.0);
}
