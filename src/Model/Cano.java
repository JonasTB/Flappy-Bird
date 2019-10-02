package Model;

import View.Tela;

public class Cano {

	// Atributos;
	public double x;
	public double y;
	public static int xVelocidade = -100;
	public static int tamanhoCano = 94; //50 pixels
	public boolean contador = false;
	public Hitbox boxCima;
	public Hitbox boxBaixo;

	// Construtor;
	public Cano(double x, double y) {

		this.x = x;
		this.y = y;
		this.boxBaixo = new Hitbox(x, y+ tamanhoCano, x+52, y+ tamanhoCano +442);
		this.boxCima = new Hitbox(x, y-270-220 ,x+52 ,y);
	}

	// Calcula a latencia, delay e repeticoes;
	public void tique(double tempo) {

		x = x + xVelocidade * tempo;
		boxCima.mover(xVelocidade * tempo, 0);
		boxBaixo.mover(xVelocidade * tempo, 0);
	}

	// Desenha na tela;
	public void tela(Tela tela) {

		// cano virado pra cima
		tela.imagem("flappy.png", 660, 0, 52, 242, 0, x, y + tamanhoCano);
		//cano virado pra cima
		tela.imagem("flappy.png", 660, 42, 52, 200, 0, x, y + tamanhoCano + 242);

		// cano virado pra baixo
		tela.imagem("flappy.png", 604, 0, 52, 270, 0, x, y - 270);
		//resto do cano virado pra baixo
		tela.imagem("flappy.png", 604, 0, 52, 220, 0, x, y - 270 - 220);

		// Ei Gilson, como foi que tu quebrou aquele cano?
	}
}
