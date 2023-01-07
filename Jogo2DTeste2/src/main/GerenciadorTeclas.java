package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GerenciadorTeclas implements KeyListener {

	public boolean cima, baixo, esquerda, direita;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int codigo = e.getKeyCode();

		if (codigo == KeyEvent.VK_W) {
			cima = true;
		}
		if (codigo == KeyEvent.VK_A) {  
			esquerda = true;
		}
		if (codigo == KeyEvent.VK_S) {
			baixo = true;
		}
		if (codigo == KeyEvent.VK_D) {
			direita = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int codigo = e.getKeyCode();
		
		if (codigo == KeyEvent.VK_W) {
			cima = false;
		}
		if (codigo == KeyEvent.VK_A) {
			esquerda = false;
		}
		if (codigo == KeyEvent.VK_S) {
			baixo = false;
		}
		if (codigo == KeyEvent.VK_D) {
			direita = false;
		}
	}

}
