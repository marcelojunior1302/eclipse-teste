package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelJogo;

public class Bota extends Item {

	public Bota() {
		nome = "Bota";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/itens/botas.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void concederBonus(int i, PainelJogo pj) {
		pj.item[i] = null;
		pj.jogador.velocidade += 2;
		
	}

	
}
