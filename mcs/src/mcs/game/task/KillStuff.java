package mcs.game.task;

import mcs.game.Game;
import mcs.net.Event;

public class KillStuff extends Task {

	@Override
	public Task run(Game game) throws InterruptedException {
		publishEvent(new Event("Killing stuff...", 5), game);
		return new KillStuff();
	}
}
