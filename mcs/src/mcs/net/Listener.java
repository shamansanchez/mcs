package mcs.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import mcs.CrazyAwesomeClass;
import mcs.game.Game;

public class Listener implements Runnable {
	private Socket sock;
	private Boolean done;

	private BlockingQueue<Event> queue;

	public Listener(Socket s) {
		sock = s;
		done = false;

		queue = new LinkedBlockingQueue<Event>();
	}

	public Boolean isDone() {
		return done;
	}

	@Override
	public void run() {
		System.out.println("connection");
		System.out.println(sock.getRemoteSocketAddress());

		PrintWriter out;
		Scanner in;

		try {
			in = new Scanner(sock.getInputStream());
			out = new PrintWriter(sock.getOutputStream());

			// TODO: Handle disconnection here...
			String name = in.nextLine();

			Game game = CrazyAwesomeClass.getGameThread(name);

			if (game == null) {
				game = CrazyAwesomeClass.startGame(name);
			}

			game.addListener(this);

			while (!this.done) {
				Event event = queue.take();
				System.out.println("sending");

				out.println(event);
				out.flush();

				System.out.println(in.hasNext());

				try {
					String response = in.nextLine();
					System.out.println(response);
					
					if(response.equals("quit")){
						this.done = true;
						in.close();
					}

				} catch (NoSuchElementException ex) {
					System.out.println("Done!");
					this.done = true;
					in.close();
				}

			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dispatch(Event event) {
		// TODO Auto-generated method stub
		try {
			queue.put(event);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}