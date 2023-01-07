package entidade;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelJogo;
import main.GerenciadorTeclas;

public class Jogador extends Entidade {

	PainelJogo pj;
	GerenciadorTeclas gTeclas;
	public final int xTela;
	public final int yTela;
	public int temChave = 0;

	public Jogador(PainelJogo pj, GerenciadorTeclas gTeclas) {
		this.pj = pj;
		this.gTeclas = gTeclas;

		xTela = pj.larguraTela / 2 - (pj.tamanhoTile / 2);
		yTela = pj.alturaTela / 2 - (pj.tamanhoTile / 2);
		
		areaSolida = new Rectangle(8, 16, pj.tamanhoTile - 16, pj.tamanhoTile - 16);
		xAreaSolidaPadrao = areaSolida.x;
		yAreaSolidaPadrao = areaSolida.y;
		definirValoresPadrao();
		pegarImagemJogador();
	}

	private void definirValoresPadrao() {

		xMundo = pj.tamanhoTile * 23;
		yMundo = pj.tamanhoTile * 21;
		velocidade = 4;
		direcao = "baixo";
	}

	private void pegarImagemJogador() {
		try {
			cima1 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_up_1.png"));
			cima2 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_up_2.png"));
			baixo1 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_down_1.png"));
			baixo2 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_down_2.png"));
			esquerda1 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_left_1.png"));
			esquerda2 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_left_2.png"));
			direita1 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_right_1.png"));
			direita2 = ImageIO.read(getClass().getResourceAsStream("/jogador/boy_right_2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void atualizar() {
		if (gTeclas.cima == true || gTeclas.baixo == true || gTeclas.esquerda == true
				|| gTeclas.direita == true) {
			if (gTeclas.cima == true) {
				direcao = "cima";
				
			} else if (gTeclas.baixo == true) {
				direcao = "baixo";
				
			} else if (gTeclas.esquerda == true) {
				direcao = "esquerda";
				
			} else if (gTeclas.direita == true) {
				direcao = "direita";
				
			}
			
			// checa colisão de tile
			colisaoAtivada = false;
			pj.cColisoes.checaTile(this);
			
			// chega colisao de item/objeto
			int indiceItem = pj.cColisoes.checaItem(this, true);
			pegarItem(indiceItem);
			
			// se colisão = false, o jogador pode se mover
			if(colisaoAtivada == false) {
				switch(direcao) {
				case "cima": yMundo -= velocidade; break;
				case "baixo": yMundo += velocidade; break;
				case "esquerda": xMundo -= velocidade; break;
				case "direita": xMundo += velocidade; break;
				}
			}

			contadorSprite++;
			if (contadorSprite > 12) {
				if (numSprite == 1) {
					numSprite = 2;
				} else if (numSprite == 2) {
					numSprite = 1;
				}
				contadorSprite = 0;
			}
		}
	}
	
	public void pegarItem(int i) {
		
		if(i != 999) {
			
			//String nomeItem = pj.item[i].nome;
			
			switch(pj.item[i].toString()) {
			case "Chave":
				pj.reproduzirEfeitoSonoro(1);
				temChave++;
				pj.item[i] = null;
				pj.ui.mostrarMensagem("Você pegou uma chave!");
				break;
			case "Porta":
				if(temChave > 0) {
					pj.reproduzirEfeitoSonoro(3);
					pj.item[i] = null;
					temChave--;
					pj.ui.mostrarMensagem("Você abriu a porta!");
				}
				else {
					pj.ui.mostrarMensagem("Você precisa de uma chave!");
				}
				
				break;
			case "Bota":
				pj.reproduzirEfeitoSonoro(2);
				velocidade += 2;
				pj.item[i] = null; 
				pj.ui.mostrarMensagem("Velocidade aumentada!");
				break;
			case "Baú":
				pj.ui.jogoFinalizado = true;
				pj.pararMusica();
				pj.reproduzirEfeitoSonoro(4);
				break;
			
			}
		}
	}

	public void desenhar(Graphics2D g2) {

		BufferedImage imagem = null;

		switch (direcao) {
		case "cima":
			if (numSprite == 1) {
				imagem = cima1;
			}
			if (numSprite == 2) {
				imagem = cima2;
			}
			break;
		case "baixo":
			if (numSprite == 1) {
				imagem = baixo1;
			}
			if (numSprite == 2) {
				imagem = baixo2;
			}
			break;
		case "esquerda":
			if (numSprite == 1) {
				imagem = esquerda1;
			}
			if (numSprite == 2) {
				imagem = esquerda2;
			}
			break;
		case "direita":
			if (numSprite == 1) {
				imagem = direita1;
			}
			if (numSprite == 2) {
				imagem = direita2;
			}
			break;
		}
		g2.drawImage(imagem, xTela, yTela, pj.tamanhoTile, pj.tamanhoTile, null);
	}
}
