package mcs.net;

public class Event {
	private String text;
	private int duration;
	
	public Event(String text, int duration){
		this.text = text;
		this.duration = duration;
	}
	
	public String getText() {
		return text;
	}
	public int getDuration() {
		return duration;
	}
	
	public String toString(){
		return String.format("%s|%d", text, duration);
	}
}
