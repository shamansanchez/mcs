package mcs.game;

import java.util.ArrayList;
public class Equipment extends Item {

	public Equipment(String name, int value, int strength, String slot) {
		super(name, value);
		this.strength = strength;
		this.slot = slot;
	}

	private int strength;
	private String slot;

	private ArrayList<Equipment> weapons = new ArrayList<Equipment>();
	private ArrayList<Equipment> armors = new ArrayList<Equipment>();
	private ArrayList<Equipment> shields = new ArrayList<Equipment>();

	private ArrayList<ArrayList<Equipment>> allEquips = new ArrayList<ArrayList<Equipment>>(){{
		add(weapons);
		add(armors);
		add(shields);
	}};


	public void getEquipmentBySlot(String slot) {
		
	}
	
	public ArrayList<ArrayList<Equipment>> getAllEquips() {
		return allEquips;
	}
	
	public String getWeakestEquip() {
		int weakestItem = Integer.MAX_VALUE;
		String weakestItemSlot ="";
		
	
		weakestItemSlot = findWeakestSlot(weakestItem, allEquips);

		return weakestItemSlot;
	}

	private String findWeakestSlot(int weakestItem, ArrayList<ArrayList<Equipment>> equips) {
		String weakestSlot = null;
		for(ArrayList<Equipment> list: equips) {
			for(Equipment equip: list) {
				if(equip.strength < weakestItem) {
					weakestSlot = equip.slot;
				}	
			}
		}
		return weakestSlot;
	}
}
