package mcs.game.task;

import mcs.game.Game;
import mcs.net.Event;


public class TownTask extends Task{

	public TownTask(Task prevTask) {
		super(prevTask);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Task run(Game game) throws InterruptedException {
		// TODO Auto-generated method stub
		return new MonsterTask(this);
	}
	
	
}
