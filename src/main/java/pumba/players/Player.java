package pumba.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import pumba.actions.Action;
import pumba.actions.impl.ActionDoNothing;
import pumba.actions.impl.ActionThrowBomb;
import pumba.actions.impl.ActionThrowDiceAgain;
import pumba.board.cells.Position;
import pumba.effects.Effect;
import pumba.users.User;

public class Player implements Comparable<Player>
{

	String username;
	Integer coins;
	Position position;
	List<Action> actions;
	Integer lastDiceResult;

	public Player(User user, Position pos)
	{
		this.username = user.getUsername();
		this.position = pos;
		this.coins = 0;
		actions = defaultActions();
	}

	public String getUsername()
	{
		return username;
	}

	public Integer getCoins()
	{
		return coins;
	}

	public void setCoins(Integer coins)
	{
		this.coins = coins;
	}

	public Position getPosition()
	{
		return position;
	}

	public void setPosition(Position position)
	{
		this.position = position;
	}

	public List<Action> getActions()
	{
		return actions;
	}

	public void setActions(List<Action> actions)
	{
		this.actions = actions;
	}

	public Integer throwDice()
	{
		lastDiceResult = new Random().nextInt(6) + 1;
		return lastDiceResult;
	}

	public void applyEffect(Effect effect)
	{
		if (effect != null && effect.getCoins() != null)
		{
			this.coins = this.coins + effect.getCoins() >= 0 ? this.coins + effect.getCoins() : 0;
		}
	}

	private List<Action> defaultActions()
	{
		List<Action> actions = new ArrayList<>();
		actions.add(new ActionThrowBomb());
		actions.add(new ActionThrowDiceAgain());
		actions.add(new ActionDoNothing());
		return actions;
	}

	public Effect playAction()
	{
		return this.actions.get(0).play();
	}

	public Effect playAction(String actionDescription)
	{
		List<Action> playerActions = this.actions.stream()
				.filter(action -> action.getActionDescription().equals(actionDescription)).collect(Collectors.toList());
		return playerActions.get(0).play();

	}

	public void grantCoins(Integer coins)
	{
		this.coins = this.coins + coins >= 0 ? this.coins + coins : 0;

	}

	@Override
	public int compareTo(Player otherPlayer)
	{
		if (this.coins > otherPlayer.getCoins())
		{
			return 1;
		}
		else if (this.coins < otherPlayer.getCoins())
		{
			return -1;
		}
		return 0;
	}

	public Integer getLastDiceResult()
	{
		return lastDiceResult;
	}

	public void setLastDiceResult(Integer lastDiceResult)
	{
		this.lastDiceResult = lastDiceResult;
	}

}
