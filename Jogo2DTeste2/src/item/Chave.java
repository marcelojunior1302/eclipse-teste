package item;

import javax.imageio.ImageIO;

import main.PainelJogo;

public class Chave extends Item {
	
	public Chave() {
		nome = "Chave";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/itens/chave.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void concederBonus(int i, PainelJogo pj) {
		pj.item[i] = null;
		pj.jogador.temChave++;
		
	}

}
