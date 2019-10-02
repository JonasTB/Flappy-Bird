package View;

import Controller.Acao;
import Controller.Jogo;
import Model.*;

import java.util.Set;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird implements Jogo, Acao {

	// Atributos;
	public Piupiu piupiu;
	public Random gerador = new Random();
	public int record = 0;
	public Pontuacao pontuacao;
	public double cenario = 0;
	public double cenarioChao = 0;
	public Contador tempoCano;
	public Hitbox chaoTracado;
	public Contador auxTempo;

	// Arraylist dos canos;
	public ArrayList<Cano> canos = new ArrayList<Cano>();

	// [ 0 -> Start View.Tela ]
	// [ 1 -> Inicia ]
	// [ 2 -> Controller.Jogo ]
	// [ 3 -> Game Over ];
	public int estadoGame = 0;

	// Adiciona a proxima cena no cenario;
	private Acao proxCena() {

		return new Acao() {

			// sobreposicao;
			@Override
			public void jogar() {

				estadoGame = estadoGame + 1;
				estadoGame = estadoGame %6;
			}

			// Metodo que para o bug dos planos parados;
			@Override
			public void executaPlanos(int tamanho) {

				return;
			}

			// Metodo que para o bug dos planos parados;
			@Override
			public void executaPlanos(float tamanho) {

				return;
			}
		};
	}

	// Adiciona os canos;
	private Acao addCano(){

		return new Acao(){

			// sobreposicao;
			@Override
			public void jogar() {

				// chama as dimensoes do cano;
				canos.add(new Cano(getLargura(),gerador.nextInt(getAltura()-112- Cano.tamanhoCano)));
			}

			// Igual na addCano;
			@Override
			public void executaPlanos(int tamanho) {

				return;
			}

			// Igual no addCano;
			@Override
			public void executaPlanos(float tamanho) {

				return;
			}
		};
	}
	
	// Construtor;
	public FlappyBird() {

		piupiu = new Piupiu(40,getAltura()/4);
		tempoCano = new Contador(2,true,addCano());
		pontuacao = new Pontuacao(0);
		chaoTracado = new Hitbox(0, getAltura()-112, getLargura(), getAltura());
	}

	// Cria o titulo na aba aberta;
	public String getTitulo(){
		return "PIUPIU VOADOR";
	}

	// Cria os criadores;
	public String getAutor(){
		return " DAN BILZERIAN E GILSÃO ";
	}

	// Cria a largura do cenario;
	public int getLargura(){
		return 384;
	}

	// Cria a altura do cenario;
	public int getAltura(){
		return 512;
	}

	// Calcula a latencia, delay e repeticoes;
	public void tique(Set<String> teclas, double tempo) {

		cenario = cenario + tempo * 25;
		cenario = cenario % 300;
		cenarioChao = cenario + tempo * 100;
		cenarioChao = cenarioChao % 310;
		
		switch(estadoGame) {

		case 0: // tela Main;

			break;

		case 1: // inicio;

			auxTempo.tique(tempo);
			piupiu.atualizaVelocidade(tempo);
			break;

		case 2: // jogo inciado;

			tempoCano.tique(tempo);
			piupiu.atualiza(tempo);
			piupiu.atualizaVelocidade(tempo);

			if(chaoTracado.intersecao(piupiu.box)!=0){
				gameOver();
				return;
			}

			if(piupiu.y<-5) {

				gameOver();
				return;
			}

			for(Cano cano: canos) {

				cano.tique(tempo);

				if(cano.boxCima.intersecao(piupiu.box)!=0 || cano.boxBaixo.intersecao(piupiu.box)!=0) {

					if(pontuacao.getPontuacao()> Pontuacao.record) {

						Pontuacao.record = pontuacao.getPontuacao();
					}

					gameOver();
					return;
				}

				if(!cano.contador && cano.x < piupiu.x) {

					cano.contador = true;
					pontuacao.alteraPontuacao(1);;
				}
			}

			if(canos.size() > 0 && canos.get(0).x < -70) {

				canos.remove(0);
			}
			
			break;

		case 3: // tela de game over;

			break;
		}
	}

	// input para receber a tecla, para fazer o piupiu voar;
	public void tecla(String tecla) {

		switch(estadoGame) {

		case 0:

			if(tecla.equals(" ")) {

				auxTempo = new Contador(1.6, false, proxCena());
				proxCena().jogar();
			}
			break;

		case 1:

			break;

		case 2:

			if(tecla.equals(" ")) {

				piupiu.flap();
			}

			break;

		case 3:

			if(tecla.equals(" ")) {

				pontuacao.setMaximo(0);
				proxCena().jogar();
			}
			break;

		}
	}

	// Desenha na tela;
	public void tela(Tela tela){

		// cenario paisagem;
		tela.imagem("flappy.png", 0, 0, 288, 512, 0,(int) -cenario, 0);
		tela.imagem("flappy.png", 0, 0, 288, 512, 0, (int) (288 - cenario), 0);
		tela.imagem("flappy.png", 0, 0, 288, 512, 0, (int) ((288*2) - cenario), 0);

		// cenario canos;
		for(Cano cano: canos){
			cano.tela(tela);
		}
		
		// cenario chao;
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, -cenarioChao, getAltura()-112);
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, 308 - cenarioChao, getAltura()-112);
		tela.imagem("flappy.png", 292, 0, 308, 112, 0, (308*2) - cenarioChao, getAltura()-112);
		
		switch(estadoGame) {

		case 0:

			tela.imagem("flappy.png", 292, 346, 192, 44, 0, getLargura()/2 - 192/2, 100);
			tela.imagem("flappy.png", 352, 306, 70, 36, 0, getLargura()/2 - 70/2, 175);
			tela.texto("Pressione espaço", 60, getAltura()/2 - 16, 32, Cor.branco);

			break;

		case 1:

			piupiu.tela(tela);
			tela.imagem("flappy.png",292,442,174,44, 0, getLargura()/2 - 174/2, getAltura()/3);

			pontuacao.desenhaPontuacao(tela, 5, 5);

			break;

		case 2:

			pontuacao.desenhaPontuacao(tela, 5, 5);
			piupiu.tela(tela);

			break;

		case 3:
			tela.imagem("flappy.png", 292, 398, 188, 38, 0, getLargura()/2 - 188/2, 100);
			tela.imagem("flappy.png", 292, 116, 226, 116, 0, getLargura()/2 - 226/2, getAltura()/2 - 116/2);

			pontuacao.desenhaPontuacao(tela, getLargura()/2 + 50, getAltura()/2-25);
			pontuacao.desenhaRecord(tela, getLargura()/2 + 55, getAltura()/2 + 16);
			break;

		}		
	}

	// Chama o gameover apos bater no cano ou no chao;;
	public void gameOver() {

		canos = new ArrayList<Cano>();
		piupiu = new Piupiu(50,getAltura()/4);
		proxCena().jogar();
	}

	// tive que chamar isso, para não bugar no abstract;
	@Override
	public void jogar() {

		return;
	}

	// Contra bugs;
	@Override
	public void executaPlanos(double tamanho) {

		return;
	}

	// Contra bugs;
	@Override
	public void executaPlanos(float tamanho) {

		return;
	}

	// Contra bugs;
	@Override
	public void executaPlanos(int tamanho) {

		return;
	}

	// Chama a classe View.Tap para iniciar a aplicacao do piupiu;
	private static void tapParaIniciar() {
		new Tap(new FlappyBird());
	}

	// Main que jogar em modo recursivo a aplicacao em static;
	public static void main(String[] args) {
        tapParaIniciar();
    }
}
