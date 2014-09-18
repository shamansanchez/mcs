package mcs.game;

public class Item {
	private String name;
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
