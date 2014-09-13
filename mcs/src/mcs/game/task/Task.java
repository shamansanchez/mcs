package mcs.game.task;

import mcs.game.Game;
import mcs.net.Event;

public abstract class Task {

	public abstract Task run(Game game) throws InterruptedException;

	public void publishEvent(Event event, Game game)
			throws InterruptedException
	{
		game.publishEvent(event);
		Thread.sleep(event.getDuration() * 1000);
	}
}