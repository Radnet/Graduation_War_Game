import controller.ControllerTabuleiro;
import model.ServerConnection;
import view.Configuracao;


public class War {

	public static void main(String[] args) {
		
		ServerConnection.GetInstance();
		ControllerTabuleiro.getInstance();
		Configuracao.getInstance();
		
		
	}

}
