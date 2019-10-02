package Model;

public class Hitbox {

    // Posições
    public static int topo = 1;
    public static int esquerdo = 2;
    public static int paisagem = 4;
    public static int direito = 8;
    
    // Canto superior esquerdo e inferior direito
    private double x0, y0, x1, y1;

    // Construtor;
    public Hitbox(double x0, double y0, double x1, double y1) {

        if(x0 < x1) {

            this.x0 = x0;
            this.x1 = x1;
        } else {

            this.x0 = x1;
            this.x1 = x0;
        }

        if(y0 < y1) {

            this.y0 = y0;
            this.y1 = y1;
        } else {

            this.y0 = y1;
            this.y1 = y0;
        }

    }

    // Metodo que move tudo que esta no hitbox para saber quando perder ou nao;
    public void mover(double x0, double y0, double x1, double y1) {

        if(x0 < x1) {

            this.x0 = x0;
            this.x1 = x1;
        } else {

            this.x0 = x1;
            this.x1 = x0;
        }

        if(y0 < y1) {

            this.y0 = y0;
            this.y1 = y1;
        } else {

            this.y0 = y1;
            this.y1 = y0;
        }

    }

    // Metodo mover;
    public void mover(double dx, double dy) {
        x0 += dx;
        x1 += dx;
        y0 += dy;
        y1 += dy;
    }
    
    // Esse retângulo colidiu com hb, e onde em hb?
    public int intersecao(Hitbox hb) {

        double w = ((x1-x0) + (hb.x1 - hb.x0)) / 2;
        double h = ((y1-y0) + (hb.y1 - hb.y0)) / 2;
        double dx = ((x1 + x0) - (hb.x1 + hb.x0)) / 2;
        double dy = ((y1 + y0) - (hb.y1 + hb.y0)) / 2;

        if (Math.abs(dx) <= w && Math.abs(dy) <= h) {

            double wy = w * dy; double hx = h * dx;
            if (wy > hx) {

                if (wy > -hx) return paisagem;
                else return esquerdo;
            } else {

                if (wy > -hx) return direito;
                else return topo;
            }
        }

        return 0;
    }

}