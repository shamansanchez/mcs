package mcs.game;

public class Item {
	private String name;
	private int value;
	
	public static Item getItemByLevel(int level) {
		return Item.getItemByGold(level * 5);
	}
	
	public static Item getItemByGold(int value) {
		return new Item("Item", value);
	}

	public Item(String name, int value) {
		this.name = name;
		this.value = value;
	}

}
