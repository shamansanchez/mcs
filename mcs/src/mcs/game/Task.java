package mcs.game;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Task {
	private int duration;

	public int getDuration() {
		return duration;
	}

	public String getText() {
		return text;
	}

	private String text;

	public Task(int length, String text) {
		this.duration = length;
		this.text = text;
	}

	public Task run(Game game) {
		Task nextTask = new Task(5, String.format("%s is doing something...", game.getId()));
		
		game.publishEvent(this.text);
		System.out.println(this.text);

		try {
			Thread.sleep(this.duration * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nextTask;
	}
}