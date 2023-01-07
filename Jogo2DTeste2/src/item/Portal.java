package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelJogo;

public class Portal extends Item {

	public Portal() {
		nome = "Portal";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/itens/portal.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void concederBonus(int i, PainelJogo pj) {
		
	}
}
