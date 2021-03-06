package pumba.board.cells;

import pumba.effects.Effect;

public abstract class Cell
{

	protected Position position = new Position();
	protected Effect effect = new Effect();
	protected Boolean walkable = false;

	
	
	public Cell(Position position, Effect effect, Boolean walkable)
	{
		super();
		this.position = position;
		this.effect = effect;
		this.walkable = walkable;
	}

	public Position getPosition()
	{
		return position;
	}

	public Effect getEffect()
	{
		return effect;
	}

	public Boolean getWalkable()
	{
		return walkable;
	}
	
	
}
