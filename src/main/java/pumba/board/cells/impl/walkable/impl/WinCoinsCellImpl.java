package main.java.pumba.board.cells.impl.walkable.impl;

import main.java.pumba.board.cells.Position;
import main.java.pumba.board.cells.impl.walkable.WalkableCell;
import main.java.pumba.effects.Effect;

public class WinCoinsCellImpl extends WalkableCell
{
	public WinCoinsCellImpl(Position pos)
	{
		super(pos, new Effect(10));
	}

}