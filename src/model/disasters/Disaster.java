package model.disasters;

import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Rescuable;
import simulation.Simulatable;
import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;

public abstract class Disaster implements Simulatable{
	private int startCycle;
	private Rescuable target;
	private boolean active;
	public Disaster(int startCycle, Rescuable target) {
		this.startCycle = startCycle;
		this.target = target;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getStartCycle() {
		return startCycle;
	}
	public Rescuable getTarget() {
		return target;
	}
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException 
	{
		if(target instanceof Citizen)
			if(((Citizen)target).getState().equals(CitizenState.DECEASED)){
				throw new CitizenAlreadyDeadException(this, "The targetted citizen is already deceased");
			}
		if(target instanceof ResidentialBuilding)
				if(((ResidentialBuilding)target).getStructuralIntegrity() == 0){
					throw new BuildingAlreadyCollapsedException(this, "The targetted building has already collapsed");
				}
		target.struckBy(this);
		active=true;
	}
}
