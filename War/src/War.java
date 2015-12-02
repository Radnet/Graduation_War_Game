import controller.ControllerTabuleiro;
import model.ServerConnection;
import view.Configuracao;


public class War {

	public static void main(String[] args) {
		
		Configuracao.getInstance();
		ControllerTabuleiro.getInstance();
		ServerConnection.GetInstance();
	}

}
