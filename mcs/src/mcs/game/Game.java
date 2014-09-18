package mcs.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import mcs.game.task.Prologue;
import mcs.game.task.Task;
import mcs.net.Event;
import mcs.net.Listener;

public class Game extends Thread {

	private List<Listener> listeners;

	public int wealth;
	public ArrayList<Item> inventory;
	
	public String race;
	public String charClass;
	
	public int level;
	public int totalExperience;
	public int expToNextLevel;
	
	public Map<String, Integer> stats;
	public Map<String, Integer> spells;
	
	public Map<String, Item> equipment;
	
	public ArrayList<String> plot;
	public ArrayList<String> quests;
	

	public Game() {
		this.listeners = Collections
				.synchronizedList(new ArrayList<Listener>());
	}

	public void addListener(Listener listener) {
		System.out.println("addListener");
		this.listeners.add(listener);
	}

	public void publishEvent(Event event) {
		ArrayList<Listener> deadListeners = new ArrayList<Listener>();

		// Add this event to the queue for any listening listeners...
		for (Listener listener : this.listeners) {
			if (!listener.isDone()) {
				listener.dispatch(event);
			} else {
				deadListeners.add(listener);
			}
		}

		// We need to take care of the dead ones here, since we can't remove
		// things while iterating in the other loop
		for (Listener listener : deadListeners) {
			this.listeners.remove(listener);
		}
	}
	
	public void addExperience(int experience){
		this.expToNextLevel -= experience;
		this.totalExperience += experience;
		
		if (this.expToNextLevel <= 0) {
			this.level += 1;
			this.expToNextLevel = this.level * 500;
		}
	}

	@Override
	public void run() {
		try {
			// Start the game with a prologue...
			Task prologue = new Prologue(null);
			Task nextTask = prologue.run(this);
			
			this.level = 1;
			this.expToNextLevel = 500;
			
			this.inventory = new ArrayList<Item>();

			// TODO: Load an existing game if possible

			// Then just run tasks forever
			while (true) {
				nextTask = nextTask.run(this);
			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
