package threads;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

import models.ClientInfo;

public class Servidor {
	
	private static Servidor servidorObj;
	
	private Map<String, ClientInfo> mapClientes;
	public boolean stopAll = false;
	public static ServerSocket servidor;
	
	private Servidor()
	{
		// Inicializa lista de clientes
		mapClientes = new HashMap<String, ClientInfo>();
	}
	
	// Metodo Singleton
	public static Servidor GetServidor()
	{
		if(servidorObj == null)
		{
			servidorObj = new Servidor();
		}
		
		return servidorObj;
	}
	
	public void Start ()
	{
		try
		{	
			// Pegando a porta a ser usada
			String pathString = System.getProperty("user.dir");
			Path path = Paths.get(pathString + "\\port.txt");
			List<String> linhasIp = Files.readAllLines(path);
			
			// Abrindo socket para comunicação com clientes
			servidor = new ServerSocket(Integer.parseInt(linhasIp.get(0)));
			
			System.out.println("Porta " + servidor + " aberta!");
			
			// Enquanto nenhum cliente interromper
			while (!stopAll) 
			{
				// Aguardando conexão com cliente
				Socket clienteSocket    = servidor.accept();
				
				// Setando informacoes do usuario
				ClientInfo clientInfo   = new ClientInfo ();
				clientInfo.ClientSocket = clienteSocket;
				clientInfo.ClientStream = new PrintStream(clienteSocket.getOutputStream());
							
				// Cria objeto da Thread para comunicacao com o cliente
				ClienteComunication clientCommunication = new ClienteComunication (clientInfo);
				Thread threadClientCommunication        = new Thread(clientCommunication);
				
				// Set Thread on client info
				clientInfo.ThreadClientCommunication = threadClientCommunication;
				
				// Mapeando cliente em um dicionario 
				mapClientes.put(clienteSocket.getRemoteSocketAddress().toString(), clientInfo);
				
				// Dispara uma thread que trata esse cliente e já espera o próximo
				clientInfo.ThreadClientCommunication.start();
			}
								
			// Fechando conexão
			servidor.close();		
			
			System.out.println("O servidor terminou de executar!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void TrataMenssagem(ClientInfo origem, String menssagem)
	{
		// Inicializa lista com os clientes de destino (todos menos a origem)
		List<ClientInfo> destinos = new ArrayList<ClientInfo> ();
		
		for(Map.Entry<String, ClientInfo> cliente : mapClientes.entrySet())
		{
			if(cliente.getValue() != origem)
			{
				destinos.add(cliente.getValue());
			}
		}
		
		//Por hora somente distribui a menssagem, no futuro teremos que tratar a menssagem e decidir o que distribuir e para quem
		DistribuiMenssagem(destinos, menssagem);
	}
	
	public void DistribuiMenssagem(List<ClientInfo> destinos, String menssagem)
	{
		for(ClientInfo cliente : destinos)
		{
			cliente.ClientStream.println(menssagem);
		}
	}
	
}
