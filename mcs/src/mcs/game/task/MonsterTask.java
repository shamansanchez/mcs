package mcs.game.task;

import mcs.game.Game;
import mcs.game.Monster;
import mcs.net.Event;

public class MonsterTask extends Task {

	public MonsterTask(Task prevTask) {
		super(prevTask);
	}

	@Override
	public Task run(Game game) throws InterruptedException {
		Monster monster = Monster.getMonster(game.level);
		publishEvent(new Event("Killing stuff...", 5), game);
		
		game.inventory.add(monster.getReward());
		
		game.addExperience(monster.getLevel() * 10);
		
		return new MonsterTask(this);
	}
}
