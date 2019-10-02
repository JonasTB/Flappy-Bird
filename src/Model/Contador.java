package Model;

import Controller.Acao;

public class Contador {

    // Atributos;
    double tempo;
    double limite;
    Acao acao;
    boolean repete;
    boolean fim;

    // Construtor;
    public Contador(double limite, boolean repete, Acao acao) {

        this.limite = limite;
        this.acao = acao;
        this.repete = repete;
    }

    // Calcula a latencia, delay e repeticoes;
    public void tique(double dt) {

        if(fim) return;
        tempo += dt;

        if(tempo > limite) {

            acao.jogar();

            if(repete) {

                tempo -= limite;
            } else {

                fim = true;
            }
        }
    }
}