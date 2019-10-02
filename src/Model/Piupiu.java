package Model;

import Controller.Acao;
import View.Tela;

public class Piupiu {

	// Atributos;
	public double x;
	public double y;
	public double velocidadeY;
	public static double gravidade = 1200;
	public static double flappy = -325;
	public Hitbox box;
	public Contador velocidadeRelogio;
	public int velocidadeEstado = 0;
	public int[] velocidadeEstados = {0,1,2,1};
	public int[] voarX = {528,528,446};
	public int[] voarY = {128,180,248};

	// Muda velocidade da acao do passaro;
	private Acao mudaVelocidade() {

		return new Acao() {

			@Override
			public void jogar() {

				velocidadeEstado = velocidadeEstado + 1;
				velocidadeEstado = velocidadeEstado % velocidadeEstados.length;
			}

			@Override
			public void executaPlanos(int tamanho) {

				return;
			}

			@Override
			public void executaPlanos(float tamanho) {

			}
		}; 	
	}

	// Construtor;
	public Piupiu(double x, double y) {

		this.x = x;
		this.y = y;
		this.velocidadeY = 0;
		this.box = new Hitbox(x, y, x+34, y+24);
		velocidadeRelogio = new Contador(0.1, true, mudaVelocidade());
	}

	// Atualiza os movimentos do passaro;
	public void atualiza(double tempo) {

		velocidadeY = velocidadeY + gravidade * tempo;
		y = y + velocidadeY * tempo;
		this.box.mover(0, velocidadeY *tempo);
	}

	// Atualiza velocidade;
	public void atualizaVelocidade(double tempo) {

		velocidadeRelogio.tique(tempo);
	}

	// Desenha na tela;
	public void tela(Tela tela) {

		tela.imagem("flappy.png", voarX[velocidadeEstados[velocidadeEstado]], voarY[velocidadeEstados[velocidadeEstado]], 34, 24, Math.atan(velocidadeY /200), x, y);
	}

	// Bater das assas;
	public void flap() {

		this.velocidadeY = flappy;
	}

}
