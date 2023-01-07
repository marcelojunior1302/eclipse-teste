package main;

import entidade.Entidade;

public class ChecadorColisoes {
	
	PainelJogo pj;

	public ChecadorColisoes(PainelJogo pj) {
		this.pj = pj;
	}
	
	public void checaTile(Entidade entidade) {
		
		int entidadeXMundoEsquerdo = entidade.xMundo + entidade.areaSolida.x;
		int entidadeXMundoDireito = entidade.xMundo + entidade.areaSolida.x + entidade.areaSolida.width;
		int entidadeYMundoSuperior = entidade.yMundo + entidade.areaSolida.y;
		int entidadeYMundoInferior = entidade.yMundo + entidade.areaSolida.y + entidade.areaSolida.height;
		
		int entidadeColEsquerda = entidadeXMundoEsquerdo/pj.tamanhoTile;
		int entidadeColDireita = entidadeXMundoDireito/pj.tamanhoTile;
		int entidadeLinSuperior = entidadeYMundoSuperior/pj.tamanhoTile;
		int entidadeLinInferior = entidadeYMundoInferior/pj.tamanhoTile;
		
		int numTile1, numTile2;
		
		switch(entidade.direcao) {
		case "cima":
			entidadeLinSuperior = (entidadeYMundoSuperior - entidade.velocidade)/pj.tamanhoTile;
			numTile1 = pj.gTiles.numTilesMapa[entidadeColEsquerda][entidadeLinSuperior];
			numTile2 = pj.gTiles.numTilesMapa[entidadeColDireita][entidadeLinSuperior];
			if(pj.gTiles.tile[numTile1].colisao == true || pj.gTiles.tile[numTile2].colisao == true) {
				entidade.colisaoAtivada = true;
			}
			break;
		case "baixo":
			entidadeLinInferior = (entidadeYMundoInferior + entidade.velocidade)/pj.tamanhoTile;
			numTile1 = pj.gTiles.numTilesMapa[entidadeColEsquerda][entidadeLinInferior];
			numTile2 = pj.gTiles.numTilesMapa[entidadeColDireita][entidadeLinInferior];
			if(pj.gTiles.tile[numTile1].colisao == true || pj.gTiles.tile[numTile2].colisao == true) {
				entidade.colisaoAtivada = true;
			}
			break;
		case "esquerda":
			entidadeColEsquerda = (entidadeXMundoEsquerdo - entidade.velocidade)/pj.tamanhoTile;
			numTile1 = pj.gTiles.numTilesMapa[entidadeColEsquerda][entidadeLinSuperior];
			numTile2 = pj.gTiles.numTilesMapa[entidadeColEsquerda][entidadeLinInferior];
			if(pj.gTiles.tile[numTile1].colisao == true || pj.gTiles.tile[numTile2].colisao == true) {
				entidade.colisaoAtivada = true;
			}
			break;
		case "direita":
			entidadeColDireita = (entidadeXMundoDireito + entidade.velocidade)/pj.tamanhoTile;
			numTile1 = pj.gTiles.numTilesMapa[entidadeColDireita][entidadeLinSuperior];
			numTile2 = pj.gTiles.numTilesMapa[entidadeColDireita][entidadeLinInferior];
			if(pj.gTiles.tile[numTile1].colisao == true || pj.gTiles.tile[numTile2].colisao == true) {
				entidade.colisaoAtivada = true;
			}
			break;
		}
	}
	public int checaItem(Entidade entidade, boolean jogador) {
		
		int indice = 999;
		
		for(int i = 0; i < pj.item.length; i++) {
			
			if(pj.item[i] != null) {
				
				// Pegar a posição da área sólida da entidade
				entidade.areaSolida.x = entidade.xMundo + entidade.areaSolida.x;
				entidade.areaSolida.y = entidade.yMundo + entidade.areaSolida.y;
				
				// Pegar a posição da área sólida do item
				pj.item[i].areaSolida.x = pj.item[i].xMundo + pj.item[i].areaSolida.x;
				pj.item[i].areaSolida.y = pj.item[i].yMundo + pj.item[i].areaSolida.y;
				
				switch(entidade.direcao) {
				case "cima":
					entidade.areaSolida.y -= entidade.velocidade;
					if(entidade.areaSolida.intersects(pj.item[i].areaSolida)) {
						if(pj.item[i].colisao == true) {
							entidade.colisaoAtivada = true;
						}
						if(jogador == true) {
							indice = i;
						}
					}
					break;
				case "baixo":
					entidade.areaSolida.y += entidade.velocidade; 
					if(entidade.areaSolida.intersects(pj.item[i].areaSolida)) {
						if(pj.item[i].colisao == true) {
							entidade.colisaoAtivada = true;
						}
						if(jogador == true) {
							indice = i;
						}
					}
					break;
				case "esquerda":
					entidade.areaSolida.x -= entidade.velocidade;
					if(entidade.areaSolida.intersects(pj.item[i].areaSolida)) {
						if(pj.item[i].colisao == true) {
							entidade.colisaoAtivada = true;
						}
						if(jogador == true) {
							indice = i;
						}
					}
					break;
				case "direita":
					entidade.areaSolida.y += entidade.velocidade;
					if(entidade.areaSolida.intersects(pj.item[i].areaSolida)) {
						if(pj.item[i].colisao == true) {
							entidade.colisaoAtivada = true;
						}
						if(jogador == true) {
							indice = i;
						}
					}
					break;	
				}
				entidade.areaSolida.x = entidade.xAreaSolidaPadrao;
				entidade.areaSolida.y = entidade.yAreaSolidaPadrao;
				pj.item[i].areaSolida.x = pj.item[i].xAreaSolidaPadrao;
				pj.item[i].areaSolida.y = pj.item[i].yAreaSolidaPadrao;		
			}
		}
		
		return indice;
	}
}
