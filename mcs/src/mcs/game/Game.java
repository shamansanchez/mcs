package mcs.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mcs.net.Listener;

public class Game implements Runnable {

	private String threadId;
	private List<Listener> listeners;

	public Game(String threadId) {
		this.threadId = threadId;
		this.listeners = Collections.synchronizedList(new ArrayList<Listener>());
	}

	public String getId() {
		return threadId;
	}
	
	public void addListener(Listener listener){
		this.listeners.add(listener);
	}
	
	public void publishEvent(String event){
		for (Listener listener : listeners) {
			listener.actionPerformed(event);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Task prologue = new Task(10, String.format(
				"An epic beginning for %s...", this.threadId));

		Task nextTask = prologue.run(this);

		while (true) {
			nextTask = nextTask.run(this);
		}
	}
}
