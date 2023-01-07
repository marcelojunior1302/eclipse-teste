package main;

import item.Bau;
import item.Bota;
import item.Chave;
import item.Porta;

public class ColocadorItem {
	
	PainelJogo pj;
	
	public ColocadorItem(PainelJogo pj) {
		this.pj = pj;
	}
	
	public void colocaItem() {
		
		pj.item[0] = new Chave();
		pj.item[0].xMundo = 23 * pj.tamanhoTile;
		pj.item[0].yMundo = 7 * pj.tamanhoTile;
		
		pj.item[1] = new Chave();
		pj.item[1].xMundo = 23 * pj.tamanhoTile;
		pj.item[1].yMundo = 40 * pj.tamanhoTile;
		
		pj.item[2] = new Chave();
		pj.item[2].xMundo = 38 * pj.tamanhoTile;
		pj.item[2].yMundo = 8 * pj.tamanhoTile;
		
		pj.item[3] = new Porta();
		pj.item[3].xMundo = 10 * pj.tamanhoTile;
		pj.item[3].yMundo = 11 * pj.tamanhoTile;
		
		pj.item[4] = new Porta();
		pj.item[4].xMundo = 8 * pj.tamanhoTile;
		pj.item[4].yMundo = 28 * pj.tamanhoTile;
		
		pj.item[5] = new Porta();
		pj.item[5].xMundo = 12 * pj.tamanhoTile;
		pj.item[5].yMundo = 22 * pj.tamanhoTile;
		
		pj.item[6] = new Bau();
		pj.item[6].xMundo = 10 * pj.tamanhoTile;
		pj.item[6].yMundo = 7 * pj.tamanhoTile;
		
		pj.item[7] = new Bota();
		pj.item[7].xMundo = 37 * pj.tamanhoTile;
		pj.item[7].yMundo = 42 * pj.tamanhoTile;
	}
	
}
