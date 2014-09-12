package mcs.net;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import mcs.CrazyAwesomeClass;
import mcs.game.Game;

public class Listener implements Runnable {
	private Socket sock;
	private Boolean done;

	public Listener(Socket s) {
		sock = s;
		done = false;
	}

	@Override
	public void run() {
		System.out.println("connection");
		System.out.println(sock.getRemoteSocketAddress());
		
		Game game = CrazyAwesomeClass.getGameThread("test");
		game.addListener(this);

		while (!this.done) {
			try {
				System.out.println("tick");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void actionPerformed(String e) {
		// TODO Auto-generated method stub
		PrintWriter out;
		Scanner in;
		
		try {
			in = new Scanner(sock.getInputStream());
			out = new PrintWriter(sock.getOutputStream());

			System.out.println("sending");

			out.println("JASON IS AWESOME");
			out.flush();

			try {
				String response = in.nextLine();
				System.out.println(response);

			} catch (NoSuchElementException ex) {
				System.out.println("Done!");
				this.done = true;
				in.close();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}