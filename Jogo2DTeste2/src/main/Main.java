package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame janela = new JFrame();
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setResizable(false);
		janela.setTitle("Portal Hunter");
		
		PainelJogo painelJogo = new PainelJogo();
		janela.add(painelJogo);
		
		janela.pack();
		
		janela.setLocationRelativeTo(null);
		janela.setVisible(true);
		
		painelJogo.setupJogo();
		painelJogo.iniciarThreadJogo();
	}

}
