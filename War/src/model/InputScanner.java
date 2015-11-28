package model;

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
				ControllerTabuleiro controller = (ControllerTabuleiro) JsonReader.jsonToJava(in_serv.nextLine());
				
				// Now we need to do something with the controller received
				
			}
			
			in_serv.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
