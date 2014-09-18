package mcs.game.task;

import java.util.ArrayList;

import mcs.game.Game;
import mcs.game.Item;
import mcs.net.Event;

public class VendorTask extends Task{
	
	public VendorTask(Task prevTask) {
		super(prevTask);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Task run(Game game) throws InterruptedException {
		
		if(!game.inventory.isEmpty()) {
			game.wealth += sellItems(game.inventory);
		}
		
		if(game.wealth != 0) {
			buyItems(game.wealth);
		}
		return new TownTask(this);
	}

	private void buyItems(int wealth) {
		
	}

	private int sellItems(ArrayList<Item> inventory) {
		int goldFromItems = 0;
		for(Item item: inventory) {
			goldFromItems += item.getValue();
			inventory.remove(item);
		}
		return goldFromItems;
	}

}
