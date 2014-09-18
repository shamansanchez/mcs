package mcs.game;

public class Monster {
	private String name;
	private int level;

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public static Monster getMonster(int level) {
		return new Monster("Monster", level);
	}

	public Item getReward() {
		Item item = Item.getItemByLevel(this.level);
		item.setName("Monster Item");
		
		return item;
	}

	public Monster(String name, int level) {
		this.name = name;
		this.level = level;
	}
}
