package mcs;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mcs.game.Game;
import mcs.net.Listener;

public class CrazyAwesomeClass {

	private static Map<String, Game> gameThreads;

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		gameThreads = Collections
				.synchronizedMap(new HashMap<String, Game>());

		startGame("Jason");

		ServerSocket ssock = null;
		try {
			ssock = new ServerSocket(2342);
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (true) {
			try {
				new Thread(new Listener(ssock.accept())).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Game getGameThread(String id) {
		return gameThreads.get(id);
	}

	public static Game startGame(String id) {
		Game game = new Game();
		game.setName(id);
		game.start();
		
		gameThreads.put(id, game);
		return gameThreads.get(id);
	}

}