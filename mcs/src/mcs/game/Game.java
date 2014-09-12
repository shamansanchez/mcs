package mcs.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mcs.net.Listener;

public class Game extends Thread {

	private List<Listener> listeners;

	public Game() {
		this.listeners = Collections
				.synchronizedList(new ArrayList<Listener>());
	}

	public void addListener(Listener listener) {
		System.out.println("addListener");
		this.listeners.add(listener);
	}

	public void publishEvent(String event) {
		ArrayList<Listener> deadListeners = new ArrayList<Listener>();

		for (Listener listener : this.listeners) {
			if (!listener.isDone()) {
				listener.dispatch(event);
			} else {
				deadListeners.add(listener);
			}
		}
		
		for (Listener listener : deadListeners) {
			this.listeners.remove(listener);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Task prologue = new Task(10, String.format(
				"An epic beginning for %s...", this.getName()));

		Task nextTask = prologue.run(this);

		while (true) {
			nextTask = nextTask.run(this);
		}
	}
}
