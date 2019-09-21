package ar.com.pumba.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import ar.com.pumba.board.cells.Cell;
import ar.com.pumba.board.cells.Position;
import ar.com.pumba.board.cells.impl.notwalkable.impl.NotPlayableCellImpl;
import ar.com.pumba.board.cells.impl.walkable.impl.CommonCellImpl;
import ar.com.pumba.board.cells.impl.walkable.impl.LoseCoinsCellImpl;
import ar.com.pumba.board.cells.impl.walkable.impl.WinCoinsCellImpl;
import ar.com.pumba.effects.Effect;
import ar.com.pumba.players.Player;

public class Board
{

	private List<Cell> cells;
	private static final Integer dimension = 20;

	public List<Cell> getCells()
	{
		return cells;
	}

	public void setCells(List<Cell> cells)
	{
		this.cells = cells;
	}

	public static Integer getDimension()
	{
		return dimension;
	}

	public Board()
	{
		super();
		List<Cell> cellsList = new ArrayList<>();
		for (int x = 0; x < dimension; x++)
		{
			for (int y = 0; y < dimension; y++)
			{
				Random rand = new Random();
				switch (rand.nextInt(4))
				{
					case 0:
						this.cells.add(new NotPlayableCellImpl(new Position(x, y)));
						break;
					case 1:
						this.cells.add(new CommonCellImpl(new Position(x, y)));
						break;
					case 2:
						this.cells.add(new LoseCoinsCellImpl(new Position(x, y)));
						break;
					case 3:
						this.cells.add(new WinCoinsCellImpl(new Position(x, y)));
						break;
					default:
						this.cells.add(new CommonCellImpl(new Position(x, y)));
						break;
				}
			}
		}
		this.cells = cellsList;
	}

	public List<Position> move(Position initialPosition, Integer steps)
	{
		// TO DO IMPLEMENT THIS METHOD
		// THERE CAN BE MORE THAN ONE POSSIBLE FINAL POSITION
		return new ArrayList<Position>();
	}

	public List<Position> move(Position initialPosition, Integer steps, Position finalPosition)
	{
		// IT VERIFIES THAT THE FINAL POSITION SENT IS A POSIBBLE FINAL POSITION
		// IF NOT, RETURNS POSSIBLE POSITIONS
		List<Position> possiblePos = move(initialPosition, steps);
		if (possiblePos.contains(finalPosition))
		{
			possiblePos.clear();
			possiblePos.add(finalPosition);
		}
		return possiblePos;
	}

	public Effect getCellEffect(Position pos)
	{
		Cell cell = cells.stream().filter(cl -> pos.equals(cl.getPosition())).collect(Collectors.toList()).get(0);
		return cell.getEffect();
	}

	public Position defaultPosition()
	{
		return new Position(0, 0);
	}
}
