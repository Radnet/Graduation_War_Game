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
				//Get the controller received from server ans store at a local variable.
				ControllerTabuleiro controllerFromServer = (ControllerTabuleiro) ois.readObject();
				
				// Set a local variable as local controller, to save some properties
				ControllerTabuleiro controllerLocal = ControllerTabuleiro.getInstance();
				
				// substitute local controller with the one received from server.
				ControllerTabuleiro.setController(controllerFromServer);
				
				// Recover meuExercito properties
				Exercito meuExercito = controllerLocal.getMeuExercito();
				if(meuExercito != null) {
					controllerFromServer.setMeuExercito(meuExercito.getNome(), (Color) meuExercito.getCor());
				}
				ois.close();
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
