package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.PainelJogo;

public class GerenciadorTiles {

	PainelJogo pj;
	public Tile[] tile;
	public int numTilesMapa[][];

	public GerenciadorTiles(PainelJogo pj) {
		this.pj = pj;

		tile = new Tile[10];
		numTilesMapa = new int[pj.maxColMundo][pj.maxLinMundo];

		pegarImagemTile();
		carregarMapa("/maps/world01.txt");
	}

	private void pegarImagemTile() {

		try {

			tile[0] = new Tile();
			tile[0].imagem = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

			tile[1] = new Tile();
			tile[1].imagem = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
			tile[1].colisao = true;

			tile[2] = new Tile();
			tile[2].imagem = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
			tile[2].colisao = true;

			tile[3] = new Tile();
			tile[3].imagem = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

			tile[4] = new Tile();
			tile[4].imagem = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
			tile[4].colisao = true;

			tile[5] = new Tile();
			tile[5].imagem = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void carregarMapa(String caminhoArquivo) {
		try {
			InputStream is = getClass().getResourceAsStream(caminhoArquivo);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int lin = 0;

			while (col < pj.maxColMundo && lin < pj.maxLinMundo) {

				String linha = br.readLine();

				while (col < pj.maxColMundo) {

					String numeros[] = linha.split(" ");

					int num = Integer.parseInt(numeros[col]);

					numTilesMapa[col][lin] = num;
					col++;
				}
				if (col == pj.maxColMundo) {
					col = 0;
					lin++;
				}
			}
			br.close();

		} catch (Exception e) {

		}
	}

	public void desenhar(Graphics2D g2) {

		int colMundo = 0;
		int linMundo = 0;

		while (colMundo < pj.maxColMundo && linMundo < pj.maxLinMundo) {

			int numTile = numTilesMapa[colMundo][linMundo];
			
			int xMundo = colMundo * pj.tamanhoTile;
			int yMundo = linMundo * pj.tamanhoTile;
			int xTela = xMundo - pj.jogador.xMundo + pj.jogador.xTela;
			int yTela = yMundo - pj.jogador.yMundo + pj.jogador.yTela;

			if(xMundo + pj.tamanhoTile > pj.jogador.xMundo - pj.jogador.xTela &&
			   xMundo - pj.tamanhoTile < pj.jogador.xMundo + pj.jogador.xTela &&
			   yMundo + pj.tamanhoTile > pj.jogador.yMundo - pj.jogador.yTela &&
			   yMundo - pj.tamanhoTile < pj.jogador.yMundo + pj.jogador.yTela) {
				
				g2.drawImage(tile[numTile].imagem, xTela, yTela, pj.tamanhoTile, pj.tamanhoTile, null);
			}
			
			colMundo++;

			if (colMundo == pj.maxColMundo) {
				colMundo = 0;
				linMundo++;
			}

		}
	}
}
