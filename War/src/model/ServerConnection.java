package model;

import java.net.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

import com.cedarsoftware.util.io.JsonWriter;

import controller.ControllerTabuleiro;

public class ServerConnection {

	private static ServerConnection serverConnection;
	private static String ip;
	private static String port;
	private static Socket servidorSocket;
	private static PrintStream servidorStream;

	private ServerConnection() {
		try {
			// Reading socket Ip and port from txt file
			String pathString = System.getProperty("user.dir");
			Path path = Paths.get(pathString + "\\resources\\ip.txt");
			List<String> linhasIp = Files.readAllLines(path);

			// Setting ip and port
			ip = linhasIp.get(0);
			port = linhasIp.get(1);
			
			System.out.println("Tentando se conectar ao servidor " + ip + " na porta " + port);

			// Opening connecting
			servidorSocket = new Socket(ip, Integer.parseInt(port));

			// Setting message stream
			servidorStream = new PrintStream(servidorSocket.getOutputStream());

			// Starting listener for server incoming messages
			StartServerListener();

			System.out.println("Conectado!");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ServerConnection GetInstance() {
		if (serverConnection == null) {
			serverConnection = new ServerConnection();
		}

		return serverConnection;
	}
	
	public void SendMessageToServer(ControllerTabuleiro controller)
	{
		GameState gameState = new GameState();
		
		gameState.lstJogadores   = controller.getLstJogadores();
		gameState.deck           = controller.getDeck();
		gameState.deckObjetivos  = controller.getDeckObjetivos();
		gameState.jogadorDaVez   = controller.getJogadorDaVez();
		//gameState.lstContinentes = controller.getLstContinentes();
		
		gameState.dicTerriSold = new HashMap<String, ArrayList<Soldado>>();
		
		for (Continente cont : controller.getLstContinentes()) 
		{
			for (Territorio terri : cont.getLstTerritorios()) 
			{
				gameState.dicTerriSold.put(terri.getNome(), terri.getLstSoldados());
			}
		}
		gameState.lstJogadas     = controller.getLstJogadas();		
		
		gameState.jogadaAtual          = controller.getJogadaAtual();  
		gameState.lstDadosAtaque       = controller.getLstDadosAtaque();
		gameState.lstDadosDefesa       = controller.getLstDadosDefesa();  
		gameState.territorioOrigem     = controller.getTerritorioOrigem();
		gameState.territorioDestino    = controller.getTerritorioDestino();
		gameState.mensagem             = controller.getMensagem();
		gameState.qtdTroca             = controller.getQtdTroca();
		gameState.conquistouTerritorio = controller.isConquistouTerritorio();
		gameState.vencedor             = controller.getVencedor();		
		
		String json = JsonWriter.objectToJson(gameState);
		System.out.println("Mensagem recebida do servidor");
		servidorStream.println(json);

	}

	public void CloseConnection() {
		try {
			// Closing all
			servidorStream.close();
			servidorSocket.close();

			System.out.println("O cliente terminou de executar!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void StartServerListener() {
		try {
			// Inicia a Thread para ouvir as menssagens do servidor
			InputScanner clientInput = new InputScanner(servidorSocket);
			Thread clientInputThread = new Thread(clientInput);
			clientInputThread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
