package pumba.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pumba.exceptions.PumbaException;
import pumba.handlers.GameHandler;
import pumba.messages.utils.SocketMessage;
import pumba.models.players.PlayerReduced;
import pumba.players.Player;

public class PlayActionMessage extends SocketMessage
{
	String actionDescription;
	String resultDescription;
	List<PlayerReduced> players = new ArrayList<>();

	public PlayActionMessage()
	{
		super(true);
	}

	@Override
	public void processResponse(Object object)
	{
		try
		{
			resultDescription = GameHandler.playAction(this.actionDescription);
			List<Player> gamePlayers = GameHandler.getPlayers();
			for (Player player : gamePlayers)
			{
				this.players.add(mapper.convertValue(player, PlayerReduced.class));
			}

			this.setApproved(true);
		}
		catch (PumbaException e)
		{
			this.setApproved(false);
			this.setErrorMessage(e.getMessage());
		}

		try
		{
			currentClient().sendMessage(this);
			sendMessageToAllOtherClients(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}

}
