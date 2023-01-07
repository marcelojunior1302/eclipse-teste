package entidade;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entidade {

	public int xMundo, yMundo;
	public int velocidade;
	
	protected BufferedImage cima1, cima2, baixo1, baixo2, esquerda1, esquerda2, direita1, direita2;
	public String direcao;
	
	protected int contadorSprite = 0;
	protected int numSprite = 1;
	public Rectangle areaSolida;
	public int xAreaSolidaPadrao, yAreaSolidaPadrao;
	public boolean colisaoAtivada = false;
}
