package Model;

import View.Tela;

public class Pontuacao {

	// Atributos;
	public int numero;
	public String pontuacaoNumero;
	public static int record = 0;

	public static int[][] numeroSalvo = {

			{576, 200},
			{578, 236},
			{578, 268},
			{578, 300},
			{574, 346},
			{574, 370},
			{330, 490},
			{350, 490},
			{370, 490},
			{390, 490}

	};

	// Construtor;
	public Pontuacao(int n){
		this.numero = n;
		setPontuacaoNumero();
	}

	// Metodo que inseri a pontuacao;
	public void setPontuacaoNumero() {

		pontuacaoNumero = String.valueOf(numero);
	}

	// Getters and Setters;
	public void setMaximo(int ponto) {

		numero = ponto;
		setPontuacaoNumero();
	}

	public int getPontuacao() {

		return numero;
	}

	public void alteraPontuacao(int num) {

		numero = numero + num;
		setPontuacaoNumero();
	}

	// Desenha a pontuacao;
	public void desenhaPontuacao(Tela t, int x, int y) {

		for(int i = 0; i< pontuacaoNumero.length(); i++) {

			desenhaNumero(t, Integer.parseInt(pontuacaoNumero.substring(i, i+1)), x + 15*i, y);
		}
	}

	// Desenha a pontuacao;
	public void desenhaRecord(Tela tela, int x, int y) {

		String srecord = String.valueOf(Pontuacao.record);

		for(int i=0; i<srecord.length(); i++) {

			desenhaNumero(tela, Integer.parseInt(srecord.substring(i, i+1)), x + 15*i, y);
		}
	}

	// Desenha a pontuacao;
	public void desenhaNumero(Tela tela, int numero, int x, int y) {

		tela.imagem("flappy.png", numeroSalvo[numero][0], numeroSalvo[numero][1], 14, 20, 0, x, y);
	}
}
