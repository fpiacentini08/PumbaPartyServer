package pumba.actions;

import pumba.effects.Effect;

public abstract class Action
{
	protected String actionDescription = "Accion abstracta";
	protected Boolean available = true;

	public abstract Effect play();

	public String getActionDescription()
	{
		return actionDescription;
	}

	public void setActionDescription(String actionDescription)
	{
		this.actionDescription = actionDescription;
	}

	public Boolean getAvailable()
	{
		return available;
	}

	public void setAvailable(Boolean available)
	{
		this.available = available;
	}

}
