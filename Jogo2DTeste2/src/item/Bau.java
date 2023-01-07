package item;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.PainelJogo;

public class Bau extends Item {

	public Bau() {
		nome = "Baú";
		try {
			imagem = ImageIO.read(getClass().getResourceAsStream("/itens/bau.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void concederBonus(int i, PainelJogo pj) {
		pj.ui.jogoFinalizado = true;
		
	}


}
