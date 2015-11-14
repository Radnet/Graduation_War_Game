package main;

import java.net.*;
import java.io.*;
import java.util.*;

import threads.InputScanner;

public class Cliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		Socket servidor = new Socket("52.91.210.31", 12345);
		
		System.out.println("O cliente se conectou ao servidor!");
		
		// Inicia a Thread para ouvir as menssagens do servidor
		InputScanner clientInput = new InputScanner (servidor);
		Thread clientInputThread = new Thread(clientInput);
		clientInputThread.start();
		
		// Inicia escuta do teclado do cliente
		Scanner teclado            = new Scanner(System.in);
		PrintStream servidorStream = new PrintStream(servidor.getOutputStream());
				
		while (true) 
		{
			String msg = teclado.nextLine();
			servidorStream.println(msg);
			
			if(msg.compareTo("exit")==0)
				break;
		}
		
		servidorStream.close();
		teclado.close();
		servidor.close();
		
		System.out.println("O cliente terminou de executar!");
	}
	
}
