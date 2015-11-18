package main;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

import threads.InputScanner;

public class Cliente {
	
	public static void main(String[] args) throws UnknownHostException, IOException 
	{
		// Reading socket Ip and port from txt file
		String pathString = System.getProperty("user.dir");
		Path path = Paths.get(pathString + "\\ip.txt");
		List<String> linhasIp = Files.readAllLines(path);
		Socket servidor = new Socket(linhasIp.get(0), Integer.parseInt(linhasIp.get(1)));
		
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
