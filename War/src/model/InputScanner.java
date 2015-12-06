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

			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
