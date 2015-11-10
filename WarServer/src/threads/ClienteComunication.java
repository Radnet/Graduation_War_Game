package threads;

import java.io.IOException;
import java.util.Scanner;

import models.ClientInfo;

public class ClienteComunication implements Runnable {
	
	ClientInfo cliente;
		
	public ClienteComunication(ClientInfo cliente) 
	{
		System.out.println("Nova conexão com o cliente " + cliente.ClientSocket.getInetAddress().getHostAddress());
		
		this.cliente = cliente; 
	}

	@Override
	public void run() 
	{
		try 
		{
			// Pegando instancia do Servidor
			Servidor servidor = Servidor.GetServidor();
			
			// Iniciando Scanner de input
			Scanner cliente_in = new Scanner(cliente.ClientSocket.getInputStream());
			
			// Lendo linhas das menssagens enviadas pelo cliente
			while (cliente_in.hasNextLine() && servidor.stopAll == false)
			{
				// Pega menssagem do cliente
				String menssagem = cliente_in.nextLine();
				
				System.out.println("Menssagem Recebida = " + menssagem);
				
				if(menssagem.equals("exit"))
				{					
					// Setando a flag que para o servidor e os demais clientes
					servidor.stopAll = true;
				}
				else
				{
					servidor.TrataMenssagem(this.cliente, menssagem);
				}
			}

			// Fechando conexões
			cliente.ClientSocket.close();
			cliente_in.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}
