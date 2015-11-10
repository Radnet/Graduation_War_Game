package threads;

import java.net.Socket;
import java.util.Scanner;

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
				System.out.println(in_serv.nextLine());
			}
			
			in_serv.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
