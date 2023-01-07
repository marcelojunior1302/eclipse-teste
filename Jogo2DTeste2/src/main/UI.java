package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import item.Chave;

public class UI {
	
	PainelJogo pj;
	Font arial_40, arial_80B;
	BufferedImage imagemChave;
	public Boolean mensagemAtivada = false;
	public String mensagem = "";
	int contadorMensagem = 0;
	public boolean jogoFinalizado = false;
	
	double tempoJogo;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(PainelJogo pj) {
		this.pj = pj;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_80B = new Font("Arial", Font.BOLD, 40);
		Chave chave = new Chave();
		imagemChave = chave.imagem;
	}
	
	public void mostrarMensagem(String texto) {
		
		mensagem = texto;
		mensagemAtivada = true;
	}
	
	public void desenhar(Graphics2D g2) {
		
		if (jogoFinalizado == true) {
			
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			
			String texto;
			int tamanhoTexto;
			int x;
			int y;
			
			texto = "Você achou o tesouro!";
			tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
			x = pj.larguraTela/2 - tamanhoTexto/2;
			y = pj.alturaTela/2 - (pj.tamanhoTile * 3);
			g2.drawString(texto, x, y);
			
			texto = "Seu tempo foi: " + dFormat.format(tempoJogo) + "s!";
			tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
			x = pj.larguraTela/2 - tamanhoTexto/2;
			y = pj.alturaTela/2 + (pj.tamanhoTile * 4);
			g2.drawString(texto, x, y);
			
			g2.setFont(arial_80B);
			g2.setColor(Color.yellow);
			texto = "Parabéns!";
			tamanhoTexto = (int)g2.getFontMetrics().getStringBounds(texto, g2).getWidth();
			x = pj.larguraTela/2 - tamanhoTexto/2;
			y = pj.alturaTela/2 + (pj.tamanhoTile * 2);
			g2.drawString(texto, x, y);
			
			pj.threadJogo = null;
		}
		else {
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			g2.drawImage(imagemChave, pj.tamanhoTile/2, pj.tamanhoTile/2, pj.tamanhoTile, pj.tamanhoTile, null);
			g2.drawString("x "+ pj.jogador.temChave, 74, 65);	
			
			// TEMPO
			tempoJogo += (double)1/60;
			g2.drawString("Tempo: " + dFormat.format(tempoJogo) +"s", pj.tamanhoTile * 10, 65); 	
			
			// MENSAGEM
			if(mensagemAtivada == true) {
				
				g2.setFont(g2.getFont().deriveFont(30F));
				g2.drawString(mensagem, pj.tamanhoTile/2, pj.tamanhoTile * 5);
				
				contadorMensagem++;
				
				if(contadorMensagem > 120) {
					contadorMensagem = 0;
					mensagemAtivada = false;
				}
			}
			
		}
		
	
	}

}
