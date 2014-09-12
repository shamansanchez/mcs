package mcs.game;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class GameThread implements Runnable {

	private String threadId;

	public GameThread(String threadId) {
		this.threadId = threadId;
	}

	public String getId() {
		return threadId;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Task prologue = new Task(10, String.format("An epic beginning for %s...", this.threadId));

		Task nextTask = prologue.run();

		while (true) {
			nextTask = nextTask.run();
		}
	}
}
