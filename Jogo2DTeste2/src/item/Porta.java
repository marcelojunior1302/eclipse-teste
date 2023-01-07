package item;

import javax.imageio.ImageIO;

import main.PainelJogo;

public class Porta extends Item {
	
	public Porta() {
		
		nome = "Porta";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/itens/porta.png"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		colisao = true;
	}
	


	@Override
	protected void concederBonus(int i, PainelJogo pj) {
		pj.item[i] = null;
		pj.jogador.temChave--;
	}

}
