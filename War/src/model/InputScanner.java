package model;

import java.awt.Color;
import java.net.Socket;
import java.util.Scanner;

import com.cedarsoftware.util.io.JsonReader;

import controller.ControllerTabuleiro;

public class InputScanner implements Runnable {

	Socket servidor;
	
	public InputScanner(Socket servidor)
	{
		this.servidor = servidor;
	}
	
	@Override
	public void run() {
		try
		{
			Scanner in_serv = new Scanner(servidor.getInputStream());
			
			while (in_serv.hasNextLine()) 
			{
				ControllerTabuleiro controllerFromServer = (ControllerTabuleiro) JsonReader.jsonToJava(in_serv.nextLine());
				
				// Set the controller received from server as the new controller without lost the "meuExercito" propertie
				ControllerTabuleiro controllerLocal = ControllerTabuleiro.getInstance();
				
				Exercito meuExercito = controllerLocal.getMeuExercito();
				
				controllerFromServer.setMeuExercito(meuExercito.getNome(), (Color) meuExercito.getCor());
				
				ControllerTabuleiro.setController(controllerFromServer);
			}
			
			in_serv.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
