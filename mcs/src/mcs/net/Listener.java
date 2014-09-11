package mcs.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
		
		while(!done){
			try {
				PrintWriter out = new PrintWriter(sock.getOutputStream());
				Scanner in = new Scanner(sock.getInputStream());
				
				System.out.println("sending");
				out.println("JASON IS AWESOME");
				out.flush();
				
				try {
					String response = in.nextLine();
					System.out.println(response);
					
				} catch (NoSuchElementException e) {
					System.out.println("Done!");
					done = true;
					in.close();
				}
				
				Thread.sleep(5000);
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}