package model;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

import com.cedarsoftware.util.io.JsonReader;

import controller.ControllerTabuleiro;

public class InputScanner implements Runnable {

	Socket 						servidor;
	
	public InputScanner(Socket servidor) throws IOException
	{
		this.servidor = servidor;
		
	}
	
	@Override
	public void run() {
		try
		{
			Scanner in_serv = new Scanner(servidor.getInputStream());
			
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
				
				controllerLocal.setJogadaAtual(gameStateFromServer.jogadaAtual                      );
				controllerLocal.setLstDadosAtaque(gameStateFromServer.lstDadosAtaque               );
				controllerLocal.setLstDadosDefesa(gameStateFromServer.lstDadosDefesa               );
				//controllerLocal.setTerritorioOrigem(gameStateFromServer.territorioOrigem           );
				//controllerLocal.setTerritorioDestino(gameStateFromServer.territorioDestino         );
				//controllerLocal.setMensagem(gameStateFromServer.mensagem                           );
				controllerLocal.setQtdTroca(gameStateFromServer.qtdTroca                           );
				controllerLocal.setConquistouTerritorio(gameStateFromServer.conquistouTerritorio   );
				controllerLocal.setVencedor(gameStateFromServer.vencedor                          );
				                                                                         
				 
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
