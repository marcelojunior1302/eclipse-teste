package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.PainelJogo;

public abstract class Item {

	public BufferedImage imagem;
	public String nome;
	public boolean colisao = false;
	public int xMundo, yMundo;
	public Rectangle areaSolida = new Rectangle(0, 0, 48, 48);
	public int xAreaSolidaPadrao = 0;
	public int yAreaSolidaPadrao = 0;
	
	public void desenhar(Graphics2D g2, PainelJogo pj) {
		
		int xTela = xMundo - pj.jogador.xMundo + pj.jogador.xTela;
		int yTela = yMundo - pj.jogador.yMundo + pj.jogador.yTela;

		if(xMundo + pj.tamanhoTile > pj.jogador.xMundo - pj.jogador.xTela &&
		   xMundo - pj.tamanhoTile < pj.jogador.xMundo + pj.jogador.xTela &&
		   yMundo + pj.tamanhoTile > pj.jogador.yMundo - pj.jogador.yTela &&
		   yMundo - pj.tamanhoTile < pj.jogador.yMundo + pj.jogador.yTela) {
			
			g2.drawImage(imagem, xTela, yTela, pj.tamanhoTile, pj.tamanhoTile, null);
		}
		
	}
	
	
	public String toString()  {
		return nome;
	}

	protected abstract void concederBonus(int i, PainelJogo pj);
	

}
