package mcs;

import java.io.IOException;
import java.net.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import mcs.game.Game;
import mcs.net.*;

public class CrazyAwesomeClass {

	private static Map<String, Game> gameThreads;

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		gameThreads = Collections
				.synchronizedMap(new HashMap<String, Game>());

		startGame("test");

		for (Map.Entry<String, Game> entry : gameThreads.entrySet()) {
			new Thread(entry.getValue()).start();
		}

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

	public static void startGame(String id) {
		gameThreads.put(id, new Game(id));
	}

}