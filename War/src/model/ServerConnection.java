package model;

import java.net.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;
import java.util.*;

import controller.ControllerTabuleiro;

public class ServerConnection {

	private static ServerConnection serverConnection;
	private static String ip;
	private static String port;
	private static Socket servidorSocket;
	private static ObjectOutputStream servidorStream;

	private ServerConnection() {
		try {
			// Reading socket Ip and port from txt file
			String pathString = System.getProperty("user.dir");
			Path path = Paths.get(pathString + "\\resources\\ip.txt");
			List<String> linhasIp = Files.readAllLines(path);

			// Setting ip and port
			ip = linhasIp.get(0);
			port = linhasIp.get(1);

			// Opening connecting
			servidorSocket = new Socket(ip, Integer.parseInt(port));

			// Setting message stream
			servidorStream = new ObjectOutputStream(servidorSocket.getOutputStream());

			// Starting listener for server incoming messages
			StartServerListener();

			System.out.println("O cliente se conectou ao servidor!");

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

	public void SendMessageToServer(ControllerTabuleiro controller) throws Exception {
		servidorStream.writeObject(controller);
		//servidorStream.close();
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
