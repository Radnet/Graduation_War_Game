package models;

import java.io.PrintStream;
import java.net.Socket;

public class ClientInfo 
{
	public Socket      ClientSocket; 
	public PrintStream ClientStream;
	public Thread      ThreadClientCommunication;
	
	public ClientInfo()
	{
	}
}
