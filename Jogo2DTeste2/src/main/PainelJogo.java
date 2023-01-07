package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entidade.Jogador;
import item.Item;
import tile.GerenciadorTiles;

public class PainelJogo extends JPanel implements Runnable {

	// CONFIGURAÇÕES DE TELA
	private final int tamanhoOriginalTile = 16; // tile 16x16
	private final int escala = 3;

	public final int tamanhoTile = tamanhoOriginalTile * escala; // 48x48 tile
	public final int tamanhoMaxCol = 16;
	public final int tamanhoMaxLin = 12;
	public final int larguraTela = tamanhoTile * tamanhoMaxCol; // 768 pixels
	public final int alturaTela = tamanhoTile * tamanhoMaxLin; // 576 pixels

	// CONFIGURAÇÕES DO MUNDO
	public final int maxColMundo = 50;
	public final int maxLinMundo = 50;
	
	// FPS
	int fps = 60;
	
	// SISTEMA
	GerenciadorTiles gTiles = new GerenciadorTiles(this);
	GerenciadorTeclas gTeclas = new GerenciadorTeclas();
	Som musica = new Som();
	Som efeitoSonoro = new Som();
	public ChecadorColisoes cColisoes = new ChecadorColisoes(this);
	public ColocadorItem cItem = new ColocadorItem(this);
	public UI ui = new UI(this);
	Thread threadJogo;
	
	// ENTIDADE E ITEM
	public Jogador jogador = new Jogador(this, gTeclas);
	public Item item[] = new Item[10];

	

	public PainelJogo() {
		
		this.setPreferredSize(new Dimension(larguraTela, alturaTela));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(gTeclas);
		this.setFocusable(true);
	}
	
	public void setupJogo() {
		
		cItem.colocaItem();
		
		reproduzirMusica(0);
	}

	public void iniciarThreadJogo() {

		threadJogo = new Thread(this);
		threadJogo.start();

	}

	@Override
	public void run() {
		
		double intervaloDesenho = 1000000000/fps; // desenha na tela a cada 0.01666 segundos
		double delta = 0;
		long tempoAnterior = System.nanoTime();
		long tempoAtual;
		long temporizador = 0;
		int contadorDesenho = 0;
		
		while (threadJogo != null) {
			
			tempoAtual = System.nanoTime();
			
			delta += (tempoAtual - tempoAnterior) / intervaloDesenho;
			temporizador += (tempoAtual - tempoAnterior);
			tempoAnterior = tempoAtual;
			
			if(delta >= 1) {
				atualizar();
				repaint();
				delta--;
				contadorDesenho++;
			}
			
			if(temporizador >= 1000000000) {
				//System.out.println("FPS:" + contadorDesenho);
				contadorDesenho = 0;
				temporizador = 0;
			}
			
			
		}

	}

	public void atualizar() {
		jogador.atualizar();
		
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;

		// TILE
		gTiles.desenhar(g2);
		
		// ITEM
		for(int i = 0; i < item.length; i++) {
			if(item[i] != null) {
				item[i].desenhar(g2, this);
			}
		}
		
		// JOGADOR
		jogador.desenhar(g2);
		
		//UI
		ui.desenhar(g2);
		
		g2.dispose();
	}
	
	public void reproduzirMusica(int i) {
		
		musica.definirArquivo(i);
		musica.reproduzir();
		musica.loop();
	}
	
	public void pararMusica() {
		
		musica.parar();
	}
	
	public void reproduzirEfeitoSonoro(int i) {
		
		efeitoSonoro.definirArquivo(i);
		efeitoSonoro.reproduzir();
	}

}
