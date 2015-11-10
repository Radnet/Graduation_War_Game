package main;

import threads.Servidor;

public class Main {

	public static void main(String[] args) {

		Servidor servidor = Servidor.GetServidor();
		servidor.Start();

	}

}
