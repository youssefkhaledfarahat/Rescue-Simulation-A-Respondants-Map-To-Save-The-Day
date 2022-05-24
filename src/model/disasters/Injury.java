package model.disasters;

import exceptions.BuildingAlreadyCollapsedException;
import exceptions.CitizenAlreadyDeadException;
import model.people.Citizen;
import model.people.CitizenState;


public class Injury extends Disaster {

	public Injury(int startCycle, Citizen target) {
		super(startCycle, target);
	}
	@Override
	public void strike() throws BuildingAlreadyCollapsedException, CitizenAlreadyDeadException 
	{
		Citizen target = (Citizen)getTarget();
		target.setBloodLoss(target.getBloodLoss()+30);
		super.strike();
	}
	@Override
	public void cycleStep() throws CitizenAlreadyDeadException {
		Citizen target = (Citizen)getTarget();
		target.setBloodLoss(target.getBloodLoss()+10);
		
	}
	public String toString(){
		return "Injury"+" ";
	}

}
