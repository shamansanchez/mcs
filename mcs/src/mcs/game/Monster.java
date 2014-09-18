package mcs.game;

public class Monster {
	private String name;
	private int level;
	private Item item;

	public static Monster getMonster(int level) {
		return new Monster("Monster", level, Item.getItemByLevel(level));
	}

	public Monster(String name, int level, Item item) {
		this.name = name;
		this.level = level;
		this.item = item;
	}
}
