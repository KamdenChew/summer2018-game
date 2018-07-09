package game;

import java.awt.Graphics;

public abstract class Creature extends Entity{
	
	protected int health;
	
	public Creature(float x, float y) {
		super(x, y);
		health = 10;
	}
	
	public int getHealth() {
		return health;
	}
}
