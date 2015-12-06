package model;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import controller.ControllerTabuleiro;

public class InputScanner implements Runnable {

	Socket 						servidor;
	static ObjectInputStream 	ois;
	
	public InputScanner(Socket servidor) throws IOException
	{
		this.servidor = servidor;
		
	}
	
	@Override
	public void run() {
		try
		{
			ois = new ObjectInputStream(servidor.getInputStream());
			
			while (true) 
			{

				GameState gameStateFromServer = (GameState) JsonReader.jsonToJava(in_serv.nextLine());
				
				// Set a local variable as local controller, to save some properties
				ControllerTabuleiro controllerLocal = ControllerTabuleiro.getInstance();
		
				controllerLocal.setLstJogadores(gameStateFromServer.lstJogadores);
				controllerLocal.setDeck(gameStateFromServer.deck);
				controllerLocal.setDeckObjetivos(gameStateFromServer.deckObjetivos);
				controllerLocal.setJogadorDaVez(gameStateFromServer.jogadorDaVez);
				controllerLocal.setLstContinentes(gameStateFromServer.lstContinentes);
				controllerLocal.setLstJogadas(gameStateFromServer.lstJogadas);
<<<<<<< HEAD

=======
				
				controllerLocal.setItJogador(gameStateFromServer.itJogador                         );
				controllerLocal.setItJogada(gameStateFromServer.itJogada                           );
				controllerLocal.setLstDadosAtaque(gameStateFromServer.lstDadosAtaque               );
				controllerLocal.setLstDadosDefesa(gameStateFromServer.lstDadosDefesa               );
				//controllerLocal.setTerritorioOrigem(gameStateFromServer.territorioOrigem           );
				//controllerLocal.setTerritorioDestino(gameStateFromServer.territorioDestino         );
				//controllerLocal.setMensagem(gameStateFromServer.mensagem                           );
				controllerLocal.setQtdTroca(gameStateFromServer.qtdTroca                           );
				controllerLocal.setConquistouTerritorio(gameStateFromServer.conquistouTerritorio   );
				controllerLocal.setVencedor(gameStateFromServer.vencedor                           );
				                                                                         
				 
>>>>>>> 444851b
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
