package mcs.game.task;

import mcs.game.Game;
import mcs.net.Event;

public class MonsterTask extends Task {

	public MonsterTask(Task prevTask) {
		super(prevTask);
	}

	@Override
	public Task run(Game game) throws InterruptedException {
		publishEvent(new Event("Killing stuff...", 5), game);
		return new MonsterTask(this);
	}
}
