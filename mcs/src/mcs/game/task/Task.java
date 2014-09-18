package mcs.game.task;

import mcs.game.Game;
import mcs.net.Event;

public abstract class Task {
	
	protected Task prevTask;
	
	public Task(Task prevTask) {
		this.prevTask = prevTask;
	}

	public abstract Task run(Game game) throws InterruptedException;

	public void publishEvent(Event event, Game game)
			throws InterruptedException
	{
		game.publishEvent(event);
		Thread.sleep(event.getDuration() * 1000);
	}
}