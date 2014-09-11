package mcs;

import java.io.IOException;
import java.net.*;

import mcs.net.*;

public class CrazyAwesomeClass {

	public static void main(String[] args) {
		ServerSocket ssock = null;
		try{
			 ssock = new ServerSocket(2342);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		while(true) {
			try{
				new Thread(new Listener(ssock.accept())).start();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

}