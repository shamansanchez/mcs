package mcs.game.task;

import mcs.game.Game;
import mcs.net.Event;

public class Prologue extends Task {

	public Prologue(Task prevTask) {
		super(prevTask);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mcs.game.Task#run(mcs.game.Game)
	 */
	@Override
	public Task run(Game game) throws InterruptedException {

		Event event1 = new Event(
				"Experiencing an enigmatic and foreboding night vision", 10);
		Event event2 = new Event(
				"Much is revealed about that wise old bastard you'd underestimated",
				6);
		Event event3 = new Event(
				"A shocking series of events leaves you alone and bewildered, but resolute",
				6);
		Event event4 = new Event(
				"Drawing upon an unrealized reserve of determination, you set out on a long and dangerous journey",
				4);
		Event event5 = new Event("Loading", 2);

		publishEvent(event1, game);
		publishEvent(event2, game);
		publishEvent(event3, game);
		publishEvent(event4, game);
		publishEvent(event5, game);

		return new MonsterTask(this);
	}
}