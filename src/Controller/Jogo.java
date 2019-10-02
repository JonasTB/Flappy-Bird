package Controller;

import View.Tela;

public interface Jogo extends Acao {

    String getTitulo();
    int getLargura();
    int getAltura();
    void tique(java.util.Set<String> teclas, double dt);
    void tecla(String tecla);
    void tela(Tela tela);
    void executaPlanos( double tamanho );
    void executaPlanos( float tamanho );
}